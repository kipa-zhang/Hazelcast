package com.kipa.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {

	public static long getTime(){
		return Calendar.getInstance().getTime().getTime();
	}
	
	public static String getFormatTime(long time,String pattern){
		SimpleDateFormat sdf = null;
		if(pattern != null && pattern != ""){
			sdf = new SimpleDateFormat(pattern);
		}else{
			sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss SSS");
		}
		String timeStamp = sdf.format(new Date(time));
		return timeStamp;
	}
	
	public static String getFormatTime(String pattern){
		SimpleDateFormat sdf = null;
		if(pattern != null && pattern != ""){
			sdf = new SimpleDateFormat(pattern);
		}else{
			sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss SSS");
		}
		String timeStamp = sdf.format(Calendar.getInstance().getTime());
		return timeStamp;
	}
}
