package com.thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 利用线程数据共享，发送消息
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-10 上午9:28:16
 */
public class SendMsgTest {

	public static void main(String[] args) throws InterruptedException {
		final Queue<String> sharedQ = new LinkedList<String>();

        Thread producer = new Producer(sharedQ);
        Thread consumer = new Consumer(sharedQ);

        producer.start();
        consumer.start();
	}
}
