package com.kipa.test.dao;

import com.kipa.model.CMS_Msg;
import com.kipa.test.db.DBHelper;

public class MsgDao {
	DBHelper db = new DBHelper();
	
	public int insert(CMS_Msg msg){
		String sql = "INSERT INTO test (name) VALUES('KIPA')";
		return db.insertReturnAutoId(sql, null);
	}
}
