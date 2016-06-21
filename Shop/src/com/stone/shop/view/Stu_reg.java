package com.stone.shop.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;

import com.stone.shop.R;
import com.stone.shop.model.Student;
import com.stone.shop.model.User;
import com.stone.util.Util;

/**
 * 注册界面
 * 
 * @date 2014-4-24
 * @author Stone
 */
public class Stu_reg extends Activity implements OnClickListener {

	@SuppressWarnings("unused")
	private static final String TAG = "RegisterActivity";

	private Button btnReg;
	private EditText etUsername;
	private EditText etPassword;
	private EditText etComfirmPsd;
	private EditText etPhone;

	private String username = null;
	private String password = null;
	private String comfirmPsd = null;
	private String email = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stu_reg);

		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		etComfirmPsd = (EditText) findViewById(R.id.et_comfirm_psd);
		etPhone = (EditText) findViewById(R.id.et_phone);

		btnReg = (Button) findViewById(R.id.btn_reg_now);
		btnReg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_reg_now:
			username = etUsername.getText().toString();
			password = etPassword.getText().toString();
			comfirmPsd = etComfirmPsd.getText().toString();
			email = etPhone.getText().toString();
			if(!Util.isNetworkConnected(this)) {
				toast("请检查网络是否已连接");
			} else if (username.equals("") || password.equals("")
					|| comfirmPsd.equals("") || email.equals("")) {
				toast("信息填写不完整 ");
			} else if (!comfirmPsd.equals(password)) {
				toast("两次密码输入不一致");
			}else if(!Util.isEmailValid(email)) {
				toast("请输入正确的邮箱地址");
			}else {
				// 开始提交注册信息
				Student bu = new Student();
				bu.setUsername(username);
				bu.setPassword(password);
				bu.setEmail(email);
				bu.signUp(this, new SaveListener() {
					@Override
					public void onSuccess() {
						toast("注册成功");
						Intent backLogin = new Intent(Stu_reg.this,
								LoginActivity.class);
						startActivity(backLogin);
						Stu_reg.this.finish();
					}

					@Override
					public void onFailure(int arg0, String msg) {
						toast("注册失败");
					}

				});
			}
			break;

		default:
			break;
		}
	}

	public void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	};

}
