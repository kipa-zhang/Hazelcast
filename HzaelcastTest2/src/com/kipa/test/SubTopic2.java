package com.kipa.test;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.kipa.model.CMS_File;

public class SubTopic2 implements MessageListener<CMS_File> {

	@Override
	public void onMessage(Message<CMS_File> message) {
		CMS_File file = message.getMessageObject();
		System.out.println("Message received " + TimeUtil.getFormatTime(TimeUtil.getTime(), null));
		System.out.println(file.getFileName() + " : " + file.getTimeStamp());
		
		try {
			FileUtil.getFile(file.getFileByte(), "e:/", file.getFileName());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("failed copy file");
			;
		}
		FileUtil.writeFile(file.getFileName(), 
				TimeUtil.getFormatTime(file.getTimeStamp(),null), 
				TimeUtil.getFormatTime(TimeUtil.getTime(),null), "e:/", "filelog.txt");
	}

	public static void main(String[] args) {
		SubTopic2 subTopic2 = new SubTopic2();

		HazelcastInstance hazelcastInstance = HazelcastClient
				.newHazelcastClient();
		ITopic topic = hazelcastInstance.getTopic("default");
		topic.addMessageListener(subTopic2);
		
		
	}

}
