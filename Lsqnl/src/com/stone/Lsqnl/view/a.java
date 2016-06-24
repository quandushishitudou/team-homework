package com.stone.Lsqnl.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.stone.Lsqnl.R;
import com.zxing.activity.CaptureActivity;

/**
 * 商品主界面
 * @date  2014-4-24
 * @author Stone
 */
public class a extends Activity implements  OnClickListener{
	
	@SuppressWarnings("unused")
	private static final String TAG = "ZxingFrame" ;
	
	
	EditText editcode;
	//ImageView imgcode;
	Button btnscan;
	private Button btnsub=null;
	public Button m_back1=null;


        
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_zxing_frame);
		  
		  m_back1=(Button)findViewById(R.id.button1);
          m_back1.setOnClickListener(
		             new Button.OnClickListener(){
		                
		                 public void onClick(View v) {
		                 	/*Intent intent = new Intent();
		                 	intent.setClass(a.this,FindResultActivity.class);
		                 	startActivity(intent);*/
		                	 Intent myintent=new Intent();
		     				String result = editcode.getText().toString();
		     				myintent.setClass(a.this, FindResultActivity.class);
		     				//Bundle mybundle=new Bundle();
		     				//mybundle.putString("number", result);
		     				myintent.putExtra("result", result);
		     				//startActivity(myintent);
		     				startActivityForResult(myintent,0);
		                 	}});
          
		editcode = (EditText) findViewById(R.id.scan_result);
		
		btnscan = (Button) findViewById(R.id.bt_bigin_scan);
		btnscan.setOnClickListener(
				new Button.OnClickListener(){
				public void onClick(View v) {
					//  Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(a.this, CaptureActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivityForResult(intent, 1);
				}
			
				});
		
	/*	btnsub = (Button) findViewById(R.id.intent2view);
		btnsub.setOnClickListener(
		     new Button.OnClickListener(){
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent myintent=new Intent();
				String result = editcode.getText().toString();
				myintent.setClass(a.this, FindResultActivity.class);
				//Bundle mybundle=new Bundle();
				//mybundle.putString("number", result);
				myintent.putExtra("number", result);
				//startActivity(myintent);
				startActivityForResult(myintent,0);
				//a.this.startActivity(myintent);
	          //  a.this.finish();			
			}
		});*/
		
		
	       
		
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
			if (resultCode == RESULT_OK) {
				Bundle bundle = data.getExtras();
				// 显示扫描到的内容
				editcode.setText(bundle.getString("result"));
				 //  String result=data.getStringExtra("number");
				
				//String result = bundle.getString("number");
				//editcode.setText(result);

				// 显示
				//imgcode.setImageBitmap((Bitmap)  bundle.getParcelable("bitmap"));
			}}
	//调用浏览器打开，功能尚未完善、、、
	public void checkResult(View v){
		String result = editcode.getText().toString();
//		Intent intent = new Intent(ZxingFrame.this,
//				CheckResult.class);
//		intent.putExtra("result", result);
//		startActivity(intent);
		
		Intent i= new Intent();          
        i.setAction("android.intent.action.VIEW");      
        Uri content_url = Uri.parse(result);     
        i.setData(content_url);             
        i.setClassName("com.android.browser","com.android.browser.BrowserActivity");     
        startActivity(i);
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}