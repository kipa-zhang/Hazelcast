package com.kipa.model;

import java.io.Serializable;

/**
 * 
 * @author KIPA
 *	注意这里的 序列化ID和包路径，在任何地方必须一致
 */
public class CMS_File implements Serializable{

	/**
	 * 序列化ID 
	 */
	private static final long serialVersionUID = 1408664537634758461L;
	
	private byte[] fileByte;
	private String fileName;
	private long fileSize;
	private String fileType;
	//发送端时间戳
	private long timeStamp;
	
	public CMS_File(){}
	
	/**
	 * @param fileContent byte[]
	 * @param fileName 
	 * @param fileSize 
	 * @param fileType like .mp4,.png and so on .
	 */
	public CMS_File(byte[] fileByte,String fileName,long fileSize,String fileType){
		this.fileByte = fileByte;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileType = fileType;
	}

	public byte[] getFileByte() {
		return fileByte;
	}
	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}
