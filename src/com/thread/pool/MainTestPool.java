package com.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-15 下午1:12:50
 */
public class MainTestPool {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(8);
		InThreadPool in1 = new InThreadPool(queue,"你好啊");
		InThreadPool in2 = new InThreadPool(queue,"非常好");
		OutThreadPool out = new OutThreadPool(queue);
		in1.start();
		in2.start();
		out.start();
	}
}
