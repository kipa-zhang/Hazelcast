package com.kipa.test.db;

public class Test {

	public static void main(String[] args) {
		DBHelper db = new DBHelper();
		String sql = "INSERT INTO test (name) VALUES('KIPA')";
		System.out.println(db.insertReturnAutoId(sql, null));
	}
}
