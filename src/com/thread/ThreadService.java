package com.thread;


/**
 * 进程测试
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-11-2 下午2:18:33
 */
public class ThreadService implements Runnable{
	
	MoneyService ms;
	private int callNum = 0;//调用次数
	
	public ThreadService(){
		this.ms = new MoneyService();//只new一次保证是同一个对象
		System.out.println(".........................");
	}
	@Override
	public void run() {
		callNum ++;
		System.out.println("第["+callNum+"]次调用");
		long num = (long)(Math.random()*1000);
		ms.getMoney(num);
	}
	
}
