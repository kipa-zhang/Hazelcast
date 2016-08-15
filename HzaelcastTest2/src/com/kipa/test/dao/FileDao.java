package com.kipa.test.dao;

import com.kipa.model.CMS_File;
import com.kipa.test.db.DBHelper;

public class FileDao {
	DBHelper db = new DBHelper();
	
	public int insert(CMS_File file){
		String sql = "INSERT INTO cms_file (filename,filesize,filepath,filetype,timestamp) VALUES('"+
											file.getFileName()+"','"+
											file.getFileSize()+"','"+
											file.getFilepath()+"','"+
											file.getFileType()+"','"+
											file.getTimeStamp()+"')";
		return db.insertReturnAutoId(sql, null);
	}
}
