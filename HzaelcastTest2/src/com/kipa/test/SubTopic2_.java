package com.kipa.test;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

public class SubTopic2_ implements MessageListener<CMS_Msg> {

	@Override
	public void onMessage(Message<CMS_Msg> message) {
		CMS_Msg msg = message.getMessageObject();
		System.out.println("Message received " + TimeUtil.getFormatTime(TimeUtil.getTime(), null));
		System.out.println(msg.getMsg() + " : " + msg.getTimeStamp());
		FileUtil.writeFile(msg.getMsg(),
				TimeUtil.getFormatTime(msg.getTimeStamp(),null),
				TimeUtil.getFormatTime(TimeUtil.getTime(), null), 
				"e:/", "log.txt");
	}

	public static void main(String[] args) {
		SubTopic2_ subTopic2 = new SubTopic2_();

		HazelcastInstance hazelcastInstance = HazelcastClient
				.newHazelcastClient();
		ITopic topic = hazelcastInstance.getTopic("default");
		topic.addMessageListener(subTopic2);
	}

}
