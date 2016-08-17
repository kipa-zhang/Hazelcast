package com.kipa.test;

import java.util.List;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.kipa.app.HazelcastApplication;

/**
 * 
 * @author KIPA
 * @param <T>
 */
public class HazelcastSubTopicListener<T> implements MessageListener<T>{

	private HazelcastInstance hazelcastInstance ;
	
	private ITopic<T> tt ;
	
	private List<T> tList;
	
	public HazelcastSubTopicListener(String topic){
		this.hazelcastInstance = HazelcastApplication.getHazelcastClientInstance();
		tt = hazelcastInstance.getTopic(topic);
	}
	
	public void onMessage(Message<T> message) {
		T t = message.getMessageObject();
		tList.add(t);
		if(t != null)
			System.out.println("Receive msg!");
	}
	
	public List<T> getCurrentMsgs(){
		return tList;
	}

	public void registerListener(){
		tt.addMessageListener(this);
	}
	
	public void close(){
		try {
			Thread.sleep(5000);
			hazelcastInstance.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		HazelcastSubTopicListener<String> hs = new HazelcastSubTopicListener<String>("default");
		hs.registerListener();
	}
}
