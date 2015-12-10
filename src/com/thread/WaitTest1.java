package com.thread;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-10 上午10:06:44
 */
public class WaitTest1 extends Thread{

	private Object obj;//多个线程间共享对象
	//结束进程标志
	private volatile static boolean stop = false;
	
	public WaitTest1(Object string){
		super("wt1");
		this.obj = string;
		System.out.println("wt1_constron");
	}
	
	public static void stopThread(){
		stop = true;
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
			synchronized (obj) {
				while(!stop){//当有这种条件存在时，还必需先改变这个参数多值让他停在循环
					System.out.println(getName()+"即将进入等待状态。。。");
					obj.wait();//wt1.interrupt();直接终止进程，会抛出下面的异常
					//使用obj.notify();唤醒线程，让它继续执行，执行完毕后就会自己终止线程
					System.out.println("。。。。。。。。");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(getName()+"等待状态解除。。。");
	}
}
