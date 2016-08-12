package com.kipa.test;

import java.util.Map;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

public class SubTopic implements MessageListener<String> {

	@Override
	public void onMessage(Message<String> message) {
		String myEvent = message.getMessageObject();
		System.out.println("Message received = " + myEvent.toString());
	}

	public static void main(String[] args) {
		SubTopic sample = new SubTopic();
		
		//最好服务端都配置IP
//		ClientConfig clientConfig = new ClientConfig();
//		clientConfig.getNetworkConfig()
//		.addAddress("10.41.87.46").addAddress("10.41.87.73");
//		HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
		
		HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient();
		ITopic topic = hazelcastInstance.getTopic("default");
		topic.addMessageListener(sample);
		
		do{
			//测试服务器间数据延迟时间差在100ms以内，更精确的没测
			Map<String,String> map = hazelcastInstance.getMap("ST");
			System.out.println(map.get("name"));
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(true);
	}
}
