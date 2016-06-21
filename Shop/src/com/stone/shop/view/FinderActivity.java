package com.stone.shop.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.stone.shop.R;

/**
 * 每日一抽页面
 * @date 2014-5-18
 * @author Stone
 */
public class FinderActivity extends Activity implements OnClickListener{
	
	@SuppressWarnings("unused")
	private static final String TAG = "FinderActivity";
	
	private Button btnCampusNews;
	private Button btnWsqToCao;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_finder);
		
		initView();
	}
	
	private void initView() {
	    btnCampusNews = (Button) findViewById(R.id.btn_campus_news);
		btnWsqToCao = (Button) findViewById(R.id.btn_wsq_tocao);
		
		btnCampusNews.setOnClickListener(this);
		btnWsqToCao.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		
		case R.id.btn_campus_news:
		
			Intent toCampusNews = new Intent(FinderActivity.this, Creat.class);
			startActivity(toCampusNews);
			break;
	
		
		
		case R.id.btn_wsq_tocao:
			Intent toWsqToCao = new Intent(FinderActivity.this, a.class);
			startActivity(toWsqToCao);
			break;
		
		
		default:
			break;
		}
	}
	

}
