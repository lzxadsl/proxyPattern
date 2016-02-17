package com.thread;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2016-1-5 上午10:26:01
 */
public class DeadLock implements Runnable{

	private Object obj;
	private Object obj1;
	
	public DeadLock(Object obj,Object obj1){
		this.obj = obj;
		this.obj1 = obj1;
	}
	
	/**
	 * 
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2016-1-5 上午10:26:22
	 */
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(Thread.currentThread().getName()+"执行。。。");
		/*if("Thread-0".equals(name)){
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(name+"执行完毕");*/
		/*if("Thread-1".equals(name)){
			try {
				Thread.sleep(5000);
				synchronized (obj) {
					obj.notify();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		synchronized (obj){
			System.out.println(Thread.currentThread().getName()+"获取锁。。。");
			try {
				//Thread.sleep(5000);
				//Thread.yield();
				//System.out.println(Thread.currentThread().getName()+"进入等待状态。。。");
				//Thread.sleep(5000);
				//obj.wait();
				while(true){}
			} catch (Exception e) {
				e.printStackTrace();
			}
			synchronized (obj1) {
				System.out.println(Thread.currentThread().getName()+"进入第二个锁");
			}
		}
		System.out.println(Thread.currentThread().getName()+"释放锁。。。");
	}

	public static void main(String[] args) throws InterruptedException {
		Object obj = new Object();
		Object obj1 = new Object();
		DeadLock lock1 = new DeadLock(obj,obj1);
		DeadLock lock2 = new DeadLock(obj,obj1);
		Thread thread1 = new Thread(lock1);
		thread1.start();
		Thread thread2 = new Thread(lock2);
		
		//thread1.join();
		thread2.start();
		System.out.println("main执行完毕");
	}
}
