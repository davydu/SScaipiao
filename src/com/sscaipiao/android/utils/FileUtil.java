package com.sscaipiao.android.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import android.util.Log;

public class FileUtil {

	
	public static String getStrFromFile(String fileName){
		File file = new File(fileName);  
		String retStr = "";
		BufferedReader reader = null;  
		try {  
		//System.out.println("以行为单位读取文件内容，一次读一整行：");  
		reader = new BufferedReader(new FileReader(file));  
		String tempString = null;  
		int line = 1;  
		//一次读入一行，直到读入null为文件结束  
		while ((tempString = reader.readLine()) != null){  
		//显示行号  
		Log.v("getStrFromFile","fileName="+fileName + " line " + line + ": " + tempString);  
		retStr += tempString+ "\n";
		line++;  
		}  
		reader.close();  
		} catch (IOException e) {  
		e.printStackTrace();  
		} finally {  
		if (reader != null){  
		try {  
		reader.close();  
		} catch (IOException e1) {  
		}  
		}  
		}
		return retStr;
	}
}
