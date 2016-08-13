package com.kipa.test.dao;

import com.kipa.model.CMS_File;
import com.kipa.test.db.DBHelper;

public class FileDao {
	DBHelper db = new DBHelper();
	
	public int insert(CMS_File file){
		String sql = "INSERT INTO test (name) VALUES('KIPA')";
		return db.insertReturnAutoId(sql, null);
	}
}
