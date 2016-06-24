package com.stone.Lsqnl.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import cn.bmob.v3.Bmob;

import com.stone.Lsqnl.R;

@SuppressWarnings("unused")
public class SplashActivity extends Activity {

	private static final String APPID = "d92c2a8a20457d95a8c9be61a840d989";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 初始化 Bmob SDK
		// 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
		Bmob.initialize(this, APPID);
		setContentView(R.layout.activity_splash);

//		// 使用推送服务时的初始化操作
//		BmobInstallation.getCurrentInstallation(this).save();
//		// 启动推送服务
//		BmobPush.startWork(this, APPID);

		mHandler.sendEmptyMessageDelayed(GO_LOGIN, 3000);
	}

	private static final int GO_HOME = 100;
	private static final int GO_LOGIN = 200;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
				break;
			case GO_LOGIN:
				Intent intent = new Intent(SplashActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
				break;
			}
		}
	};

}
