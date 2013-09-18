package com.sscaipiao.android.utils;

public class StringUtil {

	public static boolean  isEmptyString(String inputStr){

		if(inputStr == null){
			return true;
		}else if (inputStr.equals("")|| inputStr.length()<=0){
			return true;
		}else {
			return false;
		}
		
	}
	
	public static boolean isNotEmptyString(String inputStr){

		if(inputStr == null){
			return false;
		}else if (inputStr.equals("")|| inputStr.length()<=0){
			return false;
		}else {
			return true;
		}
		
	}
}
