package com.thread;

import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 线程模拟消费者
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-10 上午9:25:26
 */
public class Consumer extends Thread{

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final Queue<String> sharedQ;

    public Consumer(Queue<String> sharedQ) {
        super("Consumer");
        this.sharedQ = sharedQ;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (sharedQ) {
                //waiting condition - wait until Queue is not empty
                while (sharedQ.size() == 0) {//没有消息时处于等待状态
                    try {
                        logger.debug("Queue is empty, waiting");
                        sharedQ.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                String msg = (String) sharedQ.poll();
                logger.debug("-----------消费者收到一条消息----------- ");
                logger.debug("内容："+msg);
                sharedQ.notify();//唤醒
                if("stop".equals(msg)){
            		logger.debug("-----------收到停止发送消息指令----------- ");
            		break;
            	}
            }
        }
    }
}
