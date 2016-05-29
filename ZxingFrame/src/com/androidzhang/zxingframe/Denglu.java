package com.androidzhang.zxingframe;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Denglu extends Activity {
    public Button m_btv=null;
    public Button m_zhuce=null;
    private EditText  username=null;
    private EditText  password=null;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        setContentView(R.layout.denglu);
        
        //按钮初始化
        m_btv=(Button)findViewById(R.id.button1);
        m_zhuce=(Button)findViewById(R.id.button2);
        username = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);

        
        m_btv.setOnClickListener(
                new Button.OnClickListener(){
                   
                    public void onClick(View v) {
                    	 if(username.getText().toString().equals("zz") && password.getText().toString().equals("12345")){
                    		 Intent intent = new Intent();
                         	intent.setClass(Denglu.this,Select.class);
                         	startActivity(intent);
                    	 }
                    		   	
                        else{
                        	Toast.makeText(Denglu.this, "用户名或密码错误", 0).show();
                
                    		      }


                    	
                    	
                    	
                    }   
                });
        m_zhuce.setOnClickListener(
                new Button.OnClickListener(){
                   
                    public void onClick(View v) {
                    	Intent intent = new Intent();
                    	intent.setClass(Denglu.this,Zhuce.class);
                    	startActivity(intent);
                    }   
                });
    }
                    			
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.zxing_frame, menu);
        return true;
    }
    
}
