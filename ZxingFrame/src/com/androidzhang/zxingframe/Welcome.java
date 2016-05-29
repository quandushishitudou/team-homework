package com.androidzhang.zxingframe;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Welcome extends Activity {
	
	Timer timer = new Timer();//timer中有一个线程,这个线程不断执行task
	TimerTask task = new TimerTask() { 
	//TimerTask实现runnable接口,TimerTask类就代表一个在指定时间内执行的task
	   public void run() {
	    Intent intent = new Intent(Welcome.this, Denglu.class);
	    startActivity(intent);
	    Welcome.this.finish();
	   }
	};
	  
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		timer.schedule(task, 3000);//设置这个task在延迟三秒之后自动执行
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zxing_frame, menu);
		return true;
	}

}
