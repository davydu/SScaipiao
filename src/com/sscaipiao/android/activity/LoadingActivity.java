package com.sscaipiao.android.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;

import com.sscaipiao.android.R;


public class LoadingActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading_activity);

        SharedPreferences properties = getSharedPreferences("properties", 0);  
		Intent intent;
		if (properties == null || properties.getString("needGuide", "").equals("")) {
			 intent = new Intent(LoadingActivity.this, GuideActivity.class);
		} else {
			 intent = new Intent(LoadingActivity.this, LoginActivity.class);
		}
		
        Bundle b=new Bundle();		                            
        intent.putExtras(b);
        startActivity(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
