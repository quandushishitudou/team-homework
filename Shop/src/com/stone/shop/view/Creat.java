package com.stone.shop.view;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Hashtable;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.stone.shop.R;

/**
 * 商品主界面
 * @date  2014-4-24
 * @author Stone
 */
public class Creat extends Activity implements  OnItemClickListener{
	
	@SuppressWarnings("unused")
	private static final String TAG = "Creat" ;
	
	private int QR_WIDTH = 300;
	private int QR_HEIGHT = 300;
	private EditText resultTextView;
	protected int mScreenWidth ;
    public Button m_back=null;
    private Button create;
    private long time = Calendar.getInstance().getTimeInMillis();
    private BufferedOutputStream outStream;
        
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.creat);

		resultTextView = (EditText) this.findViewById(R.id.scan_result);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		 m_back=(Button)findViewById(R.id.button1);
	        m_back.setOnClickListener(
	                new Button.OnClickListener(){
	                   
	                    public void onClick(View v) {
	                    	Intent intent = new Intent();
	                    	intent.setClass(Creat.this,BaseActivity.class);
	                    	startActivity(intent);
	                    }   
	                });
	        initview();
	        initdata();
		
		
	}
	
	/**
	 * 初始化组件并适配数据
	 */
	private void initview() {
		// TODO Auto-generated method stub
		create = (Button) findViewById(R.id.create);
		resultTextView = (EditText) findViewById(R.id.scan_result);
	}
	private void initdata() {
		// TODO Auto-generated method stub
		/*生成二维码
		 * 
		 * */
		
		create.setOnClickListener(new OnClickListener() {

			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				if(resultTextView.getText().toString().equals("")){
					
					Toast.makeText(Creat.this, "不可输入空的内容", 0).show();}
				else{
				View view = LayoutInflater.from(Creat.this).inflate(R.layout.bitmapdialog, null);
				ImageView img = (ImageView) view.findViewById(R.id.dialog_bitmap);
				final String url = resultTextView.getText().toString();
				final Bitmap scanbitmap = createQRImage(url);
				img.setImageBitmap(scanbitmap);
				
					new AlertDialog.Builder(Creat.this)
						.setTitle("二维码")
						.setView(view)
						.setPositiveButton("存至本地",
								new DialogInterface.OnClickListener() {

									

									@SuppressLint("ShowToast")
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
											/*
											 * 将图片转换成一个byte[]；
											 * */
											byte [] bitmaps = getbitmaptobytes(scanbitmap);
											
											/*
											 * 将Byte[]转换成long类型
											 * */
											@SuppressWarnings("unused")
											long longbitmaps = bytes2long(bitmaps);
											/*
											 * 判断SD卡是否有足够的空间供下载使用
											 * */
//											boolean iscapacity = isEnaleforDownload(longbitmaps);
											if(true){
											
											try {
												File sdCardDir = Environment.getExternalStorageDirectory();
												//防止出现重复名字
												String fileName =time+".png";
												File dir;
												dir = new File(sdCardDir.getCanonicalPath()+"/DCIM/Camera/");
												if (!dir.exists()) {
													dir.mkdirs();
												}
												
												File cacheFile = new File(dir, fileName);
												FileOutputStream fstream = new FileOutputStream(cacheFile);
									            outStream = new BufferedOutputStream(fstream);
									            System.out.println("1222222");
									            outStream.write(bitmaps);
									           
									            
									            Toast.makeText(Creat.this, "图片成功存至DCIM目录下", 0).show();
									            
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
												Log.d("ydh","保存本地图片异常！！！");
											}finally{
												
												if(outStream!=null){
													try {
														outStream.close();
													} catch (IOException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												}
												//TODO 
												dialog.dismiss();
												dialog=null;
											}
										
										}  }else{
											Toast.makeText(Creat.this, "SDCard存储空间不足", 0).show();
										}

									}

									@SuppressWarnings("unused")
									private boolean isEnaleforDownload(long longbitmaps) {
										/*
										 * Statfs : 获取系统文件的类
										 * @.getAbsolutePath()给一个抽象路径名的绝对路径字符串
										 * 
										 * */
										StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
										
										//获得你的手机共有几个存储，即获得块的总量
										@SuppressWarnings("deprecation")
										int blockCount = statfs.getBlockCount();
										
										//该手机里可用的块的数量，即可用的存储。也可以说是剩余内存容量
										@SuppressWarnings("deprecation")
										int availableBlocks = statfs.getAvailableBlocks();
										
										/*
										 * 获得每一个块的大小， 返回值用long接受，int可能达到上限
										 * */
										@SuppressWarnings("deprecation")
										long blockSize = statfs.getBlockSize();
										//获得可用的存储空间
										
										long asavespace = availableBlocks * blockSize;
										
										if(asavespace>longbitmaps){
											return true;
										}
										return false;
									}

									/*
									 * 将Byte[]转换成long类型
									 * */
									private long bytes2long(byte[] bitmaps) {
										int num = 0;
										for (int ix = 0; ix < 8; ++ix) {  
											        num <<= 8;  
											        num |= (bitmaps[ix] & 0xff);  
											  }  
										return num;
									}

									/*
									 * 将图片转换成Byte[]
									 * */
									private byte[] getbitmaptobytes(Bitmap bitmap) {
										ByteArrayOutputStream out = new ByteArrayOutputStream();
										bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
										return out.toByteArray();
									}
								}).show();

			}
			}

			private Bitmap createQRImage(String url) {
				try {
					// 判断URL合法性
					if (url == null || "".equals(url) || url.length() < 1) {
						return null;
					}
					Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
					hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
					// 图像数据转换，使用了矩阵转换
//					BitMatrix bitMatrix = new MultiFormatWriter().encode(new String(url.getBytes("GBK"),"ISO-8859-1"),BarcodeFormat.QR_CODE, 300, 300);
					BitMatrix bitMatrix = new QRCodeWriter().encode(url,BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT);
					int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
					// 下面这里按照二维码的算法，逐个生成二维码的图片，
					// 两个for循环是图片横列扫描的结果
					for (int y = 0; y < QR_HEIGHT; y++) {
						for (int x = 0; x < QR_WIDTH; x++) {
							if (bitMatrix.get(x, y)) {
								pixels[y * QR_WIDTH + x] = 0xff000000;
							} else {
								pixels[y * QR_WIDTH + x] = 0xffffffff;
							}
						}
					}
					// 生成二维码图片的格式，使用ARGB_8888
					Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,Bitmap.Config.ARGB_8888);
					bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
					return bitmap;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			});
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 取得返回信息
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("scan_result");
			resultTextView.setText(scanResult);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
