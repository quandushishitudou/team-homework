package com.stone.Lsqnl.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.UpdateListener;

import com.stone.Lsqnl.R;
import com.stone.Lsqnl.model.Teacher;

/**
 * 注册界面
 * 
 * @date 2014-4-24
 * @author Stone
 */
public class ShopActivity extends Activity implements OnClickListener {

	@SuppressWarnings("unused")
	private static final String TAG = "Add";

	private Button btntijiao;
	
	private EditText etMessage;


	
	private String message = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);

		//etDnum = (EditText) findViewById(R.id.et_dnum);
		etMessage = (EditText) findViewById(R.id.et_message);
		
		btntijiao = (Button) findViewById(R.id.btn_tijiao);
		btntijiao.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_tijiao:
			//dnum = etDnum.getText().toString();
			message = etMessage.getText().toString();
			if( etMessage.getText().toString().equals("")){
				
				Toast.makeText(ShopActivity.this, "不可输入空的内容", 0).show();}
			else{
				// 开始提交注册信息
			 Teacher bu = BmobUser.getCurrentUser(this, Teacher.class);
                			

				//User bu = new User();	
				//bu.setDnum(dnum);
				bu.setMessage(message);
				bu.update(this,  bu.getObjectId(), new UpdateListener()  {
					@Override
					public void onSuccess() {
						toast("添加成功！");
						//Intent backLogin = new Intent(ShopActivity.this,
								//LoginActivity.class);
						//startActivity(backLogin);
						//ShopActivity.this.finish();
					}

					@Override
					public void onFailure(int arg0, String msg) {
						toast("添加失败！");
					}
				
				});
			}
				break;
			}
			
	}



	public void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	};

}