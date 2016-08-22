package com.kipa.test;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.kipa.app.HazelcastApplication;
import com.kipa.model.CMS_File;
import com.kipa.model.CMS_Msg;
import com.kipa.util.FileUtil;
import com.kipa.util.TimeUtil;

public class PubTopic {

	public static void main(String[] args) {
		HazelcastInstance hazelcastInstance = HazelcastApplication
				.getHazelcastInstance();
		
		ITopic<CMS_File> topic = hazelcastInstance.getTopic("default");

		// 发送信息
		// int i = 0;
		// do{
		// CMS_Msg msg = new CMS_Msg("ST" + i, TimeUtil.getTime());
		// topic.publish(msg);
		//
		// //信息间隔5秒
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		//
		// i++;
		// }while(true);

		// 发送文件
		// String filepath =
		// "C:/Users/shizhou/Desktop/hazelcast-3.6.4/vedio.mp4";
		// String filepath = "F:/HIPPO/2.zip";
		// String filepath = "F:/HIPPO/testsuite.rar";
		// String filepath = "C:/Users/shizhou/Desktop/2.jpg";
		String filepath = "C:/Users/shizhou/Desktop/hazelcast-3.6.4/lib/hazelcast-cloud-3.6.4.jar";
		// String filepath = "C:/Users/shizhou/Desktop/before.PNG";
		// String filepath = "F:/Mysql/mysql-5.5.21-winx64.msi";
		// topic.publish(FileUtil.getFile(filepath));
		// String msg =
		// "In dynamically scalable partitioned storage systems, whether it is a NoSQL database, filesystem or in-memory data grid, changes in the cluster (adding or removing a node) can lead to big data moves in the network to re-balance the cluster. Re-balancing will be needed for both primary and backup data on those nodes. If a node crashes for example, the dead node’s data has to be re-owned (become primary) by other node(s) and also its backup has to be taken immediately to be fail-safe again. Shuffling megabytes of data around has a negative effect in the cluster as it consumes your valuable resources such as network, CPU and RAM. It might also lead to higher latency of your operations during that period.";
		// topic.publish(new
		// CMS_Msg("In dynamically scalable partitioned storage systems, whether it is a NoSQL database, filesystem or in-memory data grid, changes in the cluster (adding or removing a node) can lead to big data moves in the network to re-balance the cluster. Re-balancing will be needed for both primary and backup data on those nodes. If a node crashes for example, the dead node’s data has to be re-owned (become primary) by other node(s) and also its backup has to be taken immediately to be fail-safe again. Shuffling megabytes of data around has a negative effect in the cluster as it consumes your valuable resources such as network, CPU and RAM. It might also lead to higher latency of your operations during that period.",
		// TimeUtil.getTime(null)));

		// String filepath1 = "C:/Users/shizhou/Desktop/hazelcast-3.6.4/mouse";
		// String filepath2 = ".mp4";

		// do{
		// publish 发送完消息且得到响应后才会继续往下执行Thread.sleep（）
		topic.publish(FileUtil.getFile(filepath));
		// }while(true);

//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		topic.destroy();
//		hazelcastInstance.shutdown();
		
		System.out.println("send success!");
	}
}
