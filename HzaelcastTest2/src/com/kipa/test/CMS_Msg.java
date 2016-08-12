package com.kipa.test;

import java.io.Serializable;

public class CMS_Msg implements Serializable{

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -7218328641792054856L;

	private String msg;
	private int timeStamp;
	
	public CMS_Msg() {
	}

	/**
	 * @param msg
	 * @param timeStamp
	 */
	public CMS_Msg(String msg, int timeStamp) {
		super();
		this.msg = msg;
		this.timeStamp = timeStamp;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
