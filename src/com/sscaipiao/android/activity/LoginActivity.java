package com.sscaipiao.android.activity;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sscaipiao.android.R;
import com.sscaipiao.android.common.Constants;
import com.sscaipiao.android.model.User;
import com.sscaipiao.android.utils.StringUtil;
import com.sscaipiao.android.utils.UserDataServiceHelper;

public class LoginActivity extends Activity implements OnClickListener {

	private Button loginButton;
	private EditText userName;
	private EditText password;
	private TextView loginErrorView;
	private TextView register;
	private TextView forgetPassword;
	private ImageView sinaLogin;
	private ImageView qqLogin;
	private ImageView paypalLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		loginButton = (Button) findViewById(R.id.button_login);
		userName = (EditText) findViewById(R.id.txt_username);
		password = (EditText) findViewById(R.id.txt_password);
		loginErrorView = (TextView) findViewById(R.id.txt_loginerror);
		register = (TextView) findViewById(R.id.txt_toregister);
		forgetPassword = (TextView) findViewById(R.id.txt_forget_password);
		sinaLogin = (ImageView) findViewById(R.id.sina_login_icon);
		qqLogin = (ImageView) findViewById(R.id.qq_login_icon);
		paypalLogin = (ImageView) findViewById(R.id.paypal_login_icon);

		loginButton.setOnClickListener(this);
		register.setOnClickListener(this);
		forgetPassword.setOnClickListener(this);
		sinaLogin.setOnClickListener(this);
		qqLogin.setOnClickListener(this);
		paypalLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (R.id.button_login == v.getId()) {

			// setContentView(R.layout.regist1);
			String name = String.valueOf(userName.getText());
			String pass = String.valueOf(password.getText());
			Log.v("LoginActivity.login", name + " " + pass);
			if (StringUtil.isNotEmptyString(name)
					&& StringUtil.isNotEmptyString(pass)) {
				try {
					User user = UserDataServiceHelper.login(LoginActivity.this,
							name, pass);
					if (StringUtil.isNotEmptyString(user.getToken())
							&& user.getToken().equals(
									Constants.LOGIN_SUCCESS_CODE)) {
						// go to hall
					} else {
						loginErrorView.setText(user.getMessage());
						loginErrorView.setVisibility(View.VISIBLE);
					}

				} catch (IOException e) {
					Log.v("LoginActivity.login", e.toString());
					Toast.makeText(LoginActivity.this, "网络连接失败",
							Toast.LENGTH_SHORT).show();
				}
			} else {

			}

		} else if (R.id.txt_toregister == v.getId()) {
			// 创建注册对话框
			Log.v("txt_toregister", "txt_toregister");

			Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
			Bundle b = new Bundle();
			intent.putExtras(b);
			startActivity(intent);
		} else if (R.id.txt_forget_password == v.getId()) {

		} else if (R.id.sina_login_icon == v.getId()) {

		} else if (R.id.qq_login_icon == v.getId()) {

		} else if (R.id.paypal_login_icon == v.getId()) {

		} else {

			return;
		}
	}

}
