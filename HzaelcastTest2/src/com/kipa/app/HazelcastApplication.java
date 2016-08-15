package com.kipa.app;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastApplication {

	public static HazelcastInstance getHazelcastClientInstance(){
		// 所有服务端需配置IP信息
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.getNetworkConfig().addAddress("10.41.87.46").addAddress("10.41.87.73");
		HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
		return hazelcastInstance;
	}
}
