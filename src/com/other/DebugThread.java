package com.other;

import java.io.Serializable;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-21 上午11:13:37
 */
public class DebugThread extends Thread{

	private String user = "";
	private static Object obj = new Object();
	private volatile DebugTest test;
	public DebugThread(String name){
		super(name);
		test = new DebugTest();
	}
	/**
	 * 
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-21 上午11:02:39
	 */
	@Override
	public void run() {
		int x = 5;
		int y = 10;
		//System.out.println(user);
		/*synchronized(obj){
			user = "lsl";
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		
		if("".equals(user)){
			System.out.println("if:"+user);
			user = getName();
		}else{
			System.out.println(user);
		}
	}
}
