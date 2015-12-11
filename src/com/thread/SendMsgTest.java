package com.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 利用线程数据共享，发送消息
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-10 上午9:28:16
 */
public class SendMsgTest {

	public static void main(String[] args) throws InterruptedException {
		/*final Queue<String> sharedQ = new LinkedList<String>();
        Thread producer = new Producer(sharedQ);
        Thread consumer = new Consumer(sharedQ);

        producer.start();
        consumer.start();*/
		
		testQueue();
	}
	
	/**
	 * 阻塞队列测试
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-10 下午1:33:42
	 */
	public static void testQueue(){
		final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		Thread t1 = new Thread(){
			/**
			 * 
			 * @author LiZhiXian
			 * @version 1.0
			 * @date 2015-12-10 下午1:27:32
			 */
			@Override
			public void run() {
				try {
					System.out.println("等待获取值");
					System.out.println(queue.take());//队列为空时会阻塞
					System.out.println("---------");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t2 = new Thread(){
			/**
			 * 
			 * @author LiZhiXian
			 * @version 1.0
			 * @date 2015-12-10 下午1:27:32
			 */
			@Override
			public void run() {
				try {
					queue.put("ni hao a");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//t1.interrupt();强制结束
		t2.start();
	}
}
