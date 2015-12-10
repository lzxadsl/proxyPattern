package com.thread;

import java.util.Queue;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程模拟生成者
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-10 上午9:00:04
 */
public class Producer extends Thread{

	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private final Queue<String> sharedQ;

    public Producer(Queue<String> sharedQ) {
        super("Producer");
        this.sharedQ = sharedQ;
    }

    @Override
    public void run() {
    	Scanner scan = new Scanner(System.in);
        while(true){
        	synchronized (sharedQ) {
        		System.out.println("-----------请输入-----------");
            	String msg = scan.nextLine();//等待输入
                //waiting condition - wait until Queue is not empty
                while (sharedQ.size() >= 1) {
                    try {
                        logger.debug("Queue is full, waiting");
                        sharedQ.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                logger.debug("-----------生产者发送一条消息----------- ");
                logger.debug("内容："+msg+"----------- ");
                sharedQ.add(msg);
                sharedQ.notify();//唤醒线程
                if("stop".equals(msg)){break;}
            }
        }
    }
}
