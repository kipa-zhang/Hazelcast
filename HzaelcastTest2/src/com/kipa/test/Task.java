package com.kipa.test;

import java.util.Collection;
import java.util.Map;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.kipa.model.CMS_Msg;
import com.kipa.test.db.DBHelper;

public class Task {
	public static void main(String[] args) {
		
	}
	
	public static void task(int minute){
		HazelcastInstance hazelcastInstance = HazelcastClient
				.newHazelcastClient();
		
		//message
		Map<String, CMS_Msg> msg_cache = hazelcastInstance.getMap("CMS_Msg");
		Collection<CMS_Msg> msgs = msg_cache.values();  
		System.out.println("信息个数" + msgs.size());
		
		//保存数据
		for (CMS_Msg msg : msgs) {   
			System.out.println(msg.getTimeStamp() + msg.getMsg());
			DBHelper db = new DBHelper();
			String sql = "INSERT INTO test (name) VALUES('KIPA')";
			System.out.println(db.insertReturnAutoId(sql, null));
			
		}
		//清空 Hazelcast 中的数据
		msg_cache.clear();
	}
}
