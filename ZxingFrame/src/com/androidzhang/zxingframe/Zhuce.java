package com.androidzhang.zxingframe;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;




public class Zhuce extends Activity {

	public Button m_back=null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        m_back=(Button)findViewById(R.id.button1); 
        m_back.setOnClickListener(
                new Button.OnClickListener(){
                   
                    public void onClick(View v) {
                    	Intent intent = new Intent();
                    	intent.setClass(Zhuce.this,Denglu.class);
                    	startActivity(intent);
                    }   
                });
       
    }
   
    public void onBackPressed() { 
        new AlertDialog.Builder(this).setTitle("确认退出吗？") 
            .setIcon(android.R.drawable.ic_dialog_info) 
            .setPositiveButton("确定", new DialogInterface.OnClickListener() { 
         
                @Override 
                public void onClick(DialogInterface dialog, int which) { 
                // 点击“确认”后的操作 
                    finish(); 
         
                } 
            }) 
            .setNegativeButton("取消", new DialogInterface.OnClickListener() { 
         
                @Override 
                public void onClick(DialogInterface dialog, int which) { 
                // 点击“返回”后的操作,这里不设置没有任何操作 
                } 
            }).show(); 
           }
}
