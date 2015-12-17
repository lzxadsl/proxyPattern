package com.thread.pool;

import java.util.concurrent.BlockingQueue;

/**
 * 入对立
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-15 下午1:11:57
 */
public class InThreadPool extends Thread{

	private final BlockingQueue<String> queue;
	private final String message;
	
	public InThreadPool(BlockingQueue<String> queue,String msg){
		this.queue = queue;
		this.message = msg;
	}
	
	/**
	 * 
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-15 下午1:12:16
	 */
	@Override
	public void run() {
		try {
			for(int i=0;i<50;i++){
				queue.put(message);
				System.out.println(message+i+" 加入成功。。。");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
