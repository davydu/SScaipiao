package com.sscaipiao.android.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.sscaipiao.android.common.Constants;
import com.sscaipiao.android.model.User;

public class UserDataServiceHelper {
	
	public static String getValidateCode(String phone){
		String uri = Constants.SERVER_URL + "getMobileRand.do?mobile="+phone;
		return null;
	}
	
	public static User  login(Context context, String account , String password) throws IOException{
		User user = null;
		String uri = Constants.SERVER_URL + "login.do";

		ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		pairs.add(new BasicNameValuePair("uid",account));
		pairs.add(new BasicNameValuePair("password",password));
		NetworkManager nm = new NetworkManager(context);
		String responseStr = nm.SendAndWaitResponse(pairs, uri);
		Log.v("UserDataServiceHelper.login", "response="+responseStr);
		if( StringUtil.isEmptyString(responseStr)) {
			return user;
		}
		try {
			JSONObject jsonObject = new JSONObject(responseStr);
			user = new User();
			user.setMessage(jsonObject.getString(Constants.MESSAGE));
			user.setStatus(jsonObject.getString(Constants.STATUS));
			if(user.getStatus().equals(Constants.LOGIN_SUCCESS_CODE)){
				user.setToken(jsonObject.getString(Constants.TOKEN));
			}
			user.setPassword(password);
			user.setAccount(account);
		} catch (JSONException e) {

			e.printStackTrace();
		}

		
		return user;
		
	}

	public static User  register(Context context, User user) throws IOException{
		String uri = Constants.SERVER_URL + "regUser.do";//?account="+account+"&password="+password+"&name="+name;

		ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
		pairs.add(new BasicNameValuePair("uid ",user.getAccount()));
		pairs.add(new BasicNameValuePair("password",user.getPassword()));
		pairs.add(new BasicNameValuePair("rePassword",user.getPassword()));
		pairs.add(new BasicNameValuePair("questionType",user.getQuestionType()));
		pairs.add(new BasicNameValuePair("question",user.getQuestion()));
		pairs.add(new BasicNameValuePair("answer",user.getAnswer()));
		pairs.add(new BasicNameValuePair("email",user.getEmail()));
		pairs.add(new BasicNameValuePair("name",user.getName()));
		pairs.add(new BasicNameValuePair("idCard",user.getIdCard()));
		pairs.add(new BasicNameValuePair("bank",user.getBank()));
		pairs.add(new BasicNameValuePair("province",user.getProvince()));
		pairs.add(new BasicNameValuePair("city",user.getCity()));
		pairs.add(new BasicNameValuePair("bankFullName",user.getBankFullName()));
		pairs.add(new BasicNameValuePair("bankno",user.getBankno()));
		pairs.add(new BasicNameValuePair("rand",user.getRand()));
		
		NetworkManager nm = new NetworkManager(context);
		String responseStr = nm.SendAndWaitResponse(pairs, uri);
		if( StringUtil.isEmptyString(responseStr)) {
			return user;
		}
		try {
			JSONObject jsonObject = new JSONObject(responseStr);
			user.setStatus(jsonObject.getString(Constants.STATUS));
			user.setMessage(jsonObject.getString(Constants.MESSAGE));

		} catch (JSONException e) {

			e.printStackTrace();
		}

		
		return user;
		
	}
}
