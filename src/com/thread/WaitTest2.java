package com.thread;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-10 上午10:06:44
 */
public class WaitTest2 extends Thread{

	private Object obj;//多个线程间共享对象
	public WaitTest2(Object string){
		super("wt2");
		this.obj = string;
	}
	/**
	 * 
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-10 上午10:06:55
	 */
	@Override
	public void run() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (obj) {
			WaitTest1.stopThread();
			System.out.println(getName()+"准备解除等待。。。"+System.currentTimeMillis());
			obj.notify();
		}
	}
	
	public static void main(String[] args) {
		Object obj = new Object();
        Thread wt1 = new WaitTest1(obj);
        Thread wt2 = new WaitTest2(obj);
        wt1.start();
        wt2.start();
        //Thread.sleep(1000);
        //wt1.interrupt();
	}
}
