

import java.util.List;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.DiscoveryConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.kipa.model.CMS_File;

public class Test {

	public static void main(String[] args) {
		//没法直接使用，必须开启服务端的 hazelcast (start.bat)或者使用 Test2 类中的 Client
//		ClientConfig clientConfig = new ClientConfig();
//		clientConfig.addAddress("10.41.87.73");
//		clientConfig.addAddress("10.41.87.46");
//        HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.getNetworkConfig()
						.addAddress("10.41.87.46:5701");
		
		HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap map = client.getMap( "customers" );
        List<Person> list = client.getList("asd");
        Person p = new Person();
        p.setAge(18);
        p.setName("KIPA");
        list.add(p);
        
        List<Person> list2 = client.getList("asd");
        for (Person person : list2) {
			System.out.println("delete:"+person.getName());
			list2.remove(person);
		}
        List<Person> list3 = client.getList("asd");
        for (Person person : list3) {
			System.out.println("now:"+person.getName());
		}
        
        map.set("name", "KIPA2");
//        System.out.println(map.get("st"));
//        System.out.println( "Map Size:" + map.size() );
	}
	
}
