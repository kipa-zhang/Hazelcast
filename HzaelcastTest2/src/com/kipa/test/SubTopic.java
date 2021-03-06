package com.kipa.test;

import java.io.File;
import java.util.List;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.kipa.app.HazelcastApplication;
import com.kipa.model.CMS_File;
import com.kipa.util.FileUtil;
import com.kipa.util.TimeUtil;

public class SubTopic implements MessageListener<CMS_File> {

	static HazelcastInstance hazelcastInstance = HazelcastApplication.getHazelcastClientInstance();
	
	public void onMessage(Message<CMS_File> message) {
		CMS_File file = message.getMessageObject();
		System.out.println("Message received :" + TimeUtil.getFormatTime(TimeUtil.getTime(), null));
		System.out.println(file.getFileName() + "   send: " + TimeUtil.getFormatTime(file.getTimeStamp(),null));
		
		//文件存储路径
		String filepath = "e:/files/"+TimeUtil.getFormatTime("yyyy-MM-dd");
		//保存文件
		try {
			FileUtil.saveFile(file.getFileByte(), filepath, file.getFileName());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed copy file");
		}
		//记录日志
		FileUtil.writeFile(file.getFileName()+"("+ filepath + File.separator + file.getFileName() +")", 
				TimeUtil.getFormatTime(file.getTimeStamp(),null), 
				TimeUtil.getFormatTime(TimeUtil.getTime(),null), "e:/", "filelog.txt");
		
		//将数据存储到 Hazelcast 中
		List<CMS_File> fileList = hazelcastInstance.getList("file");
		file.setFilepath(filepath + file.getFileName());
		fileList.add(file);
		
	}

	public static void main(String[] args) {
		SubTopic subTopic2 = new SubTopic();

		HazelcastInstance hazelcastInstance = HazelcastClient
				.newHazelcastClient();
		ITopic topic = hazelcastInstance.getTopic("default");
		topic.addMessageListener(subTopic2);
		
//		hazelcastInstance.shutdown();
	}

}
