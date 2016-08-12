import java.util.Map;
import java.util.Queue;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;


public class Test2 {

	public static void main( String[] args ) {
	    HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
	    Map<Integer, String> customers = hazelcastInstance.getMap( "customers" );
	    customers.put( 1, "Joe" );
	    customers.put( 2, "Ali" );
	    customers.put( 3, "Avi" );

	    System.out.println( "Customer with key 1: " + customers.get(1) );
	    System.out.println( "Map Size:" + customers.size() );

	    Queue<String> queueCustomers = hazelcastInstance.getQueue( "customers" );
	    queueCustomers.offer( "Tom" );
	    queueCustomers.offer( "Mary" );
	    queueCustomers.offer( "Jane" );
	    System.out.println( "First customer: " + queueCustomers.poll() );
	    System.out.println( "Second customer: "+ queueCustomers.peek() );
	    System.out.println( "Queue size: " + queueCustomers.size() );
	    
	    Map<Integer, String> customers2 = hazelcastInstance.getMap( "customers2" );
	    customers2.put( 1, "Joe" );
	    customers2.put( 2, "Ali" );
	    customers2.put( 3, "Avi" );

	    System.out.println( "Customer with key 1: " + customers2.get(1) );
	    System.out.println( "Map Size:" + customers2.size() );
	  }
}
