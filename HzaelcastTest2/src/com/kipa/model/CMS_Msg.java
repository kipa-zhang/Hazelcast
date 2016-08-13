package com.kipa.model;

import java.io.Serializable;

public class CMS_Msg implements Serializable{

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -7218328641792054856L;

	private String msg;
	private long timeStamp;
	
	public CMS_Msg() {
	}

	/**
	 * @param msg
	 * @param timeStamp
	 */
	public CMS_Msg(String msg, long timeStamp) {
		this.msg = msg;
		this.timeStamp = timeStamp;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
