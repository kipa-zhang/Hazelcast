package com.kipa.test;

import java.util.Map;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;

public class PubTopic {

	public static void main(String[] args) {

		// 所有服务端需配置IP信息
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.getNetworkConfig().addAddress("10.41.87.73");
		HazelcastInstance hazelcastInstance = HazelcastClient
				.newHazelcastClient(clientConfig);
		// HazelcastInstance hazelcastInstance =
		// HazelcastClient.newHazelcastClient();

		// 测试服务器端数据延迟时间差 100ms以内，更精确的待测
		ITopic topic = hazelcastInstance.getTopic("default");
		Map<String, String> map = hazelcastInstance.getMap("ST");

		int i = 0;
		do {
			map.put("name", "ST" + i);

			topic.publish("KIPA" + i);
			System.out.println("send:" + "KIPA" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
		} while (true);
	}
}
