package com.sscaipiao.android.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sscaipiao.android.R;
import com.sscaipiao.android.utils.StringUtil;

public class RegistActivity extends Activity implements OnClickListener{
	
	private Button backButton;
	private Button validateCodeButton;
	private Button finishButton;
	private TextView protocol;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist1);
		
		backButton =  (Button) findViewById(R.id.regist_button_back);
		validateCodeButton = (Button) findViewById(R.id.regist1_button_validate_code);
		finishButton = (Button) findViewById(R.id.regist_button_finish);
		protocol = (TextView) findViewById(R.id.regist_txt_protocol);
		
		backButton.setOnClickListener(this);
		validateCodeButton.setOnClickListener(this);
		finishButton.setOnClickListener(this);
		protocol.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		if(R.id.regist_button_back == v.getId()) {

			Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
			Bundle b = new Bundle();
			intent.putExtras(b);
			startActivity(intent);
		} else if ( R.id.regist_txt_protocol == v.getId()) {
			new AlertDialog.Builder(this).setTitle("用户投注协议")  
			                .setMessage(getResources().getString(R.string.user_protocol))  
			                .setPositiveButton("确定", null)  
			                .show();  
		} else if (R.id.regist1_button_validate_code == v.getId()) {
			EditText phone =  (EditText) findViewById(R.id.regist_txt_phone);
			if (StringUtil.isNotEmptyString(phone.getText().toString())){
				
			} else {
				new AlertDialog.Builder(this).setTitle("输入数据不完整")  
                .setMessage("请阅读并确认《电话购彩协议》")  
                .setPositiveButton("确定", null)  
                .show();  
			}
		} 
		else {
			return;
		}
	}
}
