package com.kipa.task;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.kipa.model.CMS_File;
import com.kipa.test.dao.FileDao;

public class FileTask extends TimerTask{

	private long delay = 0;
	private long intevalPeriod = 0; 
	private FileDao fileDao;
	
	HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
	
	/**
	 * @param delay (ms)
	 * @param intevalPeriod (ms)
	 */
	public FileTask(long delay,long intevalPeriod){
		this.delay = delay;
		this.intevalPeriod = intevalPeriod;
		fileDao = new FileDao();
	}
	
	@Override
	public void run() {
		System.out.println("Start store file info!");
		List<CMS_File> fileList = hazelcastInstance.getList("file");
		
		for (CMS_File cms_File : fileList) {
			if(fileDao.insert(cms_File) < 1 )
				fileList.remove(cms_File);
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
		FileTask ft = new FileTask(0, 1000);
		ft.excute();
	}

}
