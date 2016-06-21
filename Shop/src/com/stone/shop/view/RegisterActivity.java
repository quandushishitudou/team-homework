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
import com.stone.shop.model.Teacher;
import com.stone.shop.model.User;
import com.stone.util.Util;

/**
 * 注册界面
 * 
 * @date 2014-4-24
 * @author Stone
 */
public class RegisterActivity extends Activity implements OnClickListener {

	@SuppressWarnings("unused")
	private static final String TAG = "RegisterActivity";

	private Button btnReg;
	private EditText etUsername;
	private EditText etPassword;
	private EditText etComfirmPsd;
	private EditText etPhone;
	private EditText etRname;
	private EditText etDnum;


	private String username = null;
	private String password = null;
	private String comfirmPsd = null;
	private String email = null;
	private String rname = null;
	private String dnum = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);

		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		etComfirmPsd = (EditText) findViewById(R.id.et_comfirm_psd);
		etPhone = (EditText) findViewById(R.id.et_phone);
		etRname = (EditText) findViewById(R.id.et_rname);
		etDnum = (EditText) findViewById(R.id.et_dnum);

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
			email= etPhone.getText().toString();
			rname = etRname.getText().toString();
			dnum = etDnum.getText().toString();
			if(!Util.isNetworkConnected(this)) {
				toast(" 请确保网络已连接 ");
			} else if (username.equals("") || password.equals("")
					|| comfirmPsd.equals("") || email.equals("")) {
				
				toast("信息添加不完整，无法完成注册 ");
			} else if (!comfirmPsd.equals(password)) {
				toast("两次密码输入不一致");
			} else if(!Util.isEmailValid(email)) {
				toast("请输入正确的邮箱地址");
			}
			else {
				// 开始提交注册信息
				Teacher bu = new Teacher();
				bu.setUsername(username);
				bu.setPassword(password);
				bu.setEmail(email);
				bu.setDnum(dnum);
				bu.setRname(rname);
				bu.signUp(this, new SaveListener() {
					@Override
					public void onSuccess() {
						toast("注册成功");
						Intent backLogin = new Intent(RegisterActivity.this,
								LoginActivity.class);
						startActivity(backLogin);
						RegisterActivity.this.finish();
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
