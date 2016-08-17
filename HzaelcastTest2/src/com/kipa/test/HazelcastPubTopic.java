package com.kipa.test;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.kipa.app.HazelcastApplication;
import com.kipa.model.CMS_File;
import com.kipa.util.FileUtil;

/**
 * 
 * @author KIPA
 * @param <T>
 */
public class HazelcastPubTopic<T> {

	private HazelcastInstance hazelcastInstance ;
	private ITopic<T> tt ;
	
	public HazelcastPubTopic(String topic){
		this.hazelcastInstance = HazelcastApplication.getHazelcastClientInstance();
		tt = hazelcastInstance.getTopic(topic);
	}
	
	/**
	 * publish Object
	 * @param t
	 * @return
	 */
	public boolean pub(T t){
		tt.publish(t);
		
		return true;
	}
	
	/**
	 * Close HazelcastClient Instance
	 */
	public void close(){
		try {
			//make sure something has been done
			Thread.sleep(5);
			this.hazelcastInstance.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Close HazelcastClient Instance failed!");
		}
	}

	public static void main(String[] args) {
		/**
		 * get the send object
		 */
		String filepath = "C:/Users/shizhou/Desktop/TestFiles/image_1M.jpg";
		CMS_File file = FileUtil.getFile(filepath);
		
		/**
		 *  CMS_File is the object will be sent
		 */
		HazelcastPubTopic<CMS_File> hazelcast = new HazelcastPubTopic<CMS_File>("default");
		
		/**
		 * if publish success,will return true
		 */
		if(hazelcast.pub(file)){
			hazelcast.close();
		}
	}
	
}
