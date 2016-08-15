package com.kipa.task;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.kipa.model.CMS_Msg;
import com.kipa.test.dao.MsgDao;

public class MsgTask extends TimerTask{

	private long delay = 0;
	private long intevalPeriod = 0; 
	
	private MsgDao msgDao;
	
	private HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
	
	/**
	 * @param delay (ms)
	 * @param intevalPeriod (ms)
	 */
	public MsgTask(long delay,long intevalPeriod){
		this.delay = delay;
		this.intevalPeriod = intevalPeriod;
		msgDao = new MsgDao();
	}
	
	@Override
	public void run() {
		System.out.println("Start store msg info!");
		
		List<CMS_Msg> msgList = hazelcastInstance.getList("msg");
		
		for (CMS_Msg cms_Msg : msgList) {
			if(msgDao.insert(cms_Msg) < 1)
				msgList.remove(cms_Msg);
		}
	}

	public void excute(){
		Timer timer = new Timer();  
        // schedules the task to be run in an interval  
        timer.scheduleAtFixedRate(this, delay, intevalPeriod);
	}
	
	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public long getIntevalPeriod() {
		return intevalPeriod;
	}

	public void setIntevalPeriod(long intevalPeriod) {
		this.intevalPeriod = intevalPeriod;
	}
	
	public static void main(String[] args) {
		MsgTask ft = new MsgTask(0, 1000);
		ft.excute();
	}
}
