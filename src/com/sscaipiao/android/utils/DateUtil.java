package com.sscaipiao.android.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	

	public  String getTimeFrom(String patten , Date date) {

		if(date == null){
		 date = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		int retHour = cal.get(Calendar.HOUR_OF_DAY);
		int retMin = cal.get(Calendar.MINUTE);
		
		StringBuffer sb = new StringBuffer();
		if(retHour<10){
			sb.append("0");
		}
		sb.append(retHour);
		sb.append(":");
		if(retMin<10){
			sb.append("0");
		}
		sb.append(retMin);
		//System.out.println(sb.toString());
		return sb.toString();
		//return "10:45";

	}
	
	/* (non-Javadoc)
	 * @see util.IDataUtil#getDateFromServer(java.lang.String)
	 */
	public  String getDate(String patten, Date date) {
		if(date == null){
			date = new Date();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		int retYear = cal.get(Calendar.YEAR);
		int retMonth = cal.get(Calendar.MONTH)+1;
		int retDay = cal.get(Calendar.DAY_OF_MONTH);
		
		StringBuffer sb = new StringBuffer();
		sb.append(retYear);
		sb.append("-");
		if(retMonth<10){
			sb.append("0");
		}
		sb.append(retMonth);
		sb.append("-");
		if(retDay<10){
			sb.append("0");
		}
		sb.append(retDay);
//		System.out.println("**********getDateFromServer");
//	System.out.println(sb.toString());
		return sb.toString();
		//return "2009-07-30";

	}

	public boolean stringDateCompare(String date1, String date2 , String split) {

		int time1 = Integer.parseInt(date1.substring(0, date1.indexOf(split))) * 100
				+ Integer.parseInt(date1.substring(date1.indexOf(split)+1));

		int time2 = Integer.parseInt(date2.substring(0, date2.indexOf(split))) * 100
				+ Integer.parseInt(date2.substring(date2.indexOf(split)+1));

		return time1 > time2;
	}
	

	
	public  Date getDate(String time){
		if(time==null||time.equals(""))
			return null;
		try {
			Calendar ca = Calendar.getInstance();
			int year = Integer.parseInt(time.substring(0, 4));
			System.out.println(".....year:"+year);
			int month = Integer.parseInt(time.substring(5, 7));
			System.out.println(".....month:"+month);
			int day = Integer.parseInt(time.substring(8, 10));
			System.out.println(".....day:"+day);
			int hour=Integer.parseInt(time.substring(11, 13));
			System.out.println(".....hour:"+hour);
			int minute=Integer.parseInt(time.substring(14, 16));
			System.out.println(".....minute:"+minute);
			ca.set(Calendar.YEAR, year);
			ca.set(Calendar.MONTH, month-1);
			ca.set(Calendar.DATE, day);
			ca.set(Calendar.HOUR_OF_DAY, hour);
			ca.set(Calendar.MINUTE, minute);
			ca.set(Calendar.SECOND, 0);
			return ca.getTime();
		} catch (Exception e) {
			System.out.println("[commonfunction] getDate error");
			e.printStackTrace();
			return null;
		}
	}

	public String calculateGap(Date oldDate, Date newDate){
		if(oldDate == null || newDate == null ){
			return "0 小时前";
		}
		long d1 = oldDate.getTime();
		long d2 = newDate.getTime();
		
		long hours = (d2 - d1) / (1000 * 60 * 60);
		
		if(hours >=24) {
			return (hours / 24) + " 天前"; 
		} else {
			return hours + " 小时前";
		}
		
	}
	
	
}
