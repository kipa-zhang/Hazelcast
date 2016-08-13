package com.kipa.test.dao;

import com.kipa.test.db.DBHelper;

public class FileDao {
	DBHelper db = new DBHelper();
	
	public void insert(){
		String sql = "INSERT INTO test (name) VALUES('KIPA')";
		System.out.println(db.insertReturnAutoId(sql, null));
	}
}
