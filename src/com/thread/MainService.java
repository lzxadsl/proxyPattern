package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-11-2 下午2:30:40
 */
public class MainService {
   
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec =  Executors.newCachedThreadPool();//调用封装好的线程池
		//ThreadPoolExecutor tPool = new ThreadPoolExecutor(10, 20,200, TimeUnit.MICROSECONDS,new ArrayBlockingQueue<Runnable>(10));
		ThreadService ts = new ThreadService();
		for(int i=0;i<5;i++){
			Thread t = new Thread(ts);
			//t.start();
			exec.execute(t);
		}
		exec.shutdown();
	}
	
	
}
