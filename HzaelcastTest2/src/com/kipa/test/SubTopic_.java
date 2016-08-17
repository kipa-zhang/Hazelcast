package com.kipa.test;

import java.util.List;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.kipa.app.HazelcastApplication;
import com.kipa.model.CMS_Msg;
import com.kipa.util.FileUtil;
import com.kipa.util.TimeUtil;

public class SubTopic_ implements MessageListener<CMS_Msg> {

	static HazelcastInstance hazelcastInstance = HazelcastApplication.getHazelcastClientInstance();
	
	public void onMessage(Message<CMS_Msg> message) {
		CMS_Msg msg = message.getMessageObject();
		System.out.println("Message received " + TimeUtil.getFormatTime(TimeUtil.getTime(), null));
		System.out.println(msg.getMsg() + "   send: " + TimeUtil.getFormatTime(msg.getTimeStamp(), null));
		FileUtil.writeFile(msg.getMsg(),
				TimeUtil.getFormatTime(msg.getTimeStamp(),null),
				TimeUtil.getFormatTime(TimeUtil.getTime(), null), 
				"e:/", "cmslog.txt");
		
		//将数据存储到 Hazelcast 中
		List<CMS_Msg> msgList = hazelcastInstance.getList("msg");
		msgList.add(msg);
	}

	public static void main(String[] args) {
		SubTopic_ subTopic2 = new SubTopic_();

		
		ITopic topic = hazelcastInstance.getTopic("default");
		topic.addMessageListener(subTopic2);
		
//		hazelcastInstance.shutdown();
	}

}
