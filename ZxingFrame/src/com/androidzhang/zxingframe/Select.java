package com.androidzhang.zxingframe;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;


public class Select extends Activity {

	public Button m_sm=null;
	public Button m_sc=null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        
        m_sm=(Button)findViewById(R.id.button2);
        m_sm.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                    	Intent intent = new Intent();
                    	intent.setClass(Select.this,ZxingFrame.class);
                    	startActivity(intent);
                    }   
                });
        m_sc=(Button)findViewById(R.id.button1);
        m_sc.setOnClickListener(
                new Button.OnClickListener(){
                   
                    public void onClick(View v) {
                    	Intent intent = new Intent();
                    	intent.setClass(Select.this,Creat.class);
                    	startActivity(intent);
                    }   
                });
        
       
    }

}
