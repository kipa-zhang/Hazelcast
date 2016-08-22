package com.kipa.app;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastApplication {

	public static HazelcastInstance getHazelcastClientInstance(){
		// 所有服务端需配置IP信息
//		ClientConfig clientConfig = new ClientConfig();
//		clientConfig.getNetworkConfig().addAddress("10.41.87.46")/*.addAddress("10.41.87.56")*/;
//		HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);
//		return hazelcastInstance;
		
		//multicast,使用的默认的配置文件
		return HazelcastClient.newHazelcastClient();
	}
	
	public static HazelcastInstance getHazelcastInstance(){
		// 所有服务端需配置IP信息
//		Config config = new Config();
//		NetworkConfig nc = new NetworkConfig();
//		nc.setPublicAddress("10.41.87.73");
//		config.setNetworkConfig(nc);
//		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
		
		//multicast，使用默认的配置文件
		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
		
		return hazelcastInstance;
	}
}
