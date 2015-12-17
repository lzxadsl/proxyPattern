package com.thread.pool;

import java.util.concurrent.BlockingQueue;

/**
 * 出队列
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-15 下午1:12:28
 */
public class OutThreadPool extends Thread{

	private final BlockingQueue<String> queue;

	public OutThreadPool(BlockingQueue<String> queue){
		this.queue = queue;
	}
	
	/**
	 * 
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-15 下午1:25:29
	 */
	@Override
	public void run(){
		try {
			while(true){
				if(!queue.offer("")){
					for(int i=0;i<8;i++){
						System.out.println("获取消息："+queue.take());
					}
					System.out.println("休息一下...");
					sleep(10000);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
