package com.androidzhang.zxingframe;

import com.zxing.activity.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ZxingFrame extends Activity implements OnClickListener {

	EditText editcode;
	//ImageView imgcode;
	Button btnscan;
	public Button m_back1=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_zxing_frame);
		  m_back1=(Button)findViewById(R.id.button1);
		     m_back1.setOnClickListener(
		             new Button.OnClickListener(){
		                
		                 public void onClick(View v) {
		                 	Intent intent = new Intent();
		                 	intent.setClass(ZxingFrame.this,Select.class);
		                 	startActivity(intent);}});
		editcode = (EditText) findViewById(R.id.scan_result);
		//imgcode = (ImageView) findViewById(R.id.imgcode);
		btnscan = (Button) findViewById(R.id.bt_bigin_scan);
		btnscan.setOnClickListener(this);
	}

	@Override 
	public void onClick(View v) {
		// if(v.getId()== R.id.bt_bigin_scan) {
		
			Intent intent = new Intent();
			intent.setClass(ZxingFrame.this, CaptureActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivityForResult(intent, 1);
		// }
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
			if (resultCode == RESULT_OK) {
				Bundle bundle = data.getExtras();
				// 显示扫描到的内容
				editcode.setText(bundle.getString("result"));
				// 显示
				//imgcode.setImageBitmap((Bitmap)  bundle.getParcelable("bitmap"));
			}}
			//调用浏览器打开，功能尚未完善、、、
			public void checkResult(View v){
				String result = editcode.getText().toString();
//				Intent intent = new Intent(ZxingFrame.this,
//						CheckResult.class);
//				intent.putExtra("result", result);
//				startActivity(intent);
				
				Intent i= new Intent();          
		        i.setAction("android.intent.action.VIEW");      
		        Uri content_url = Uri.parse(result);     
		        i.setData(content_url);             
		        i.setClassName("com.android.browser","com.android.browser.BrowserActivity");     
		        startActivity(i);
			}
		
	}


	
		
	


