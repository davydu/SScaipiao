package com.sscaipiao.android.activity;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sscaipiao.android.R;
import com.sscaipiao.android.common.Constants;
import com.sscaipiao.android.model.User;
import com.sscaipiao.android.utils.StringUtil;
import com.sscaipiao.android.utils.UserDataServiceHelper;

public class LoginActivity extends Activity implements OnClickListener{
	
	private Button loginButton;
	private EditText userName;
	private EditText password;
	private TextView loginErrorView ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		loginButton = (Button) findViewById(R.id.button_login);
		userName = (EditText) findViewById(R.id.txt_username);
		password = (EditText) findViewById(R.id.txt_password);
		loginErrorView = (TextView)findViewById(R.id.txt_loginerror);
		loginButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		String name = String.valueOf(userName.getText());
		String pass = String.valueOf(password.getText());
		Log.v("LoginActivity.login", name + " "+ pass);
		if(StringUtil.isNotEmptyString(name) && StringUtil.isNotEmptyString(pass)) {
			try {
				User user = UserDataServiceHelper.login(LoginActivity.this, name, pass);
				if(StringUtil.isNotEmptyString( user.getToken())  &&  user.getToken().equals(Constants.LOGIN_SUCCESS_CODE)){
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
	}
	
}
