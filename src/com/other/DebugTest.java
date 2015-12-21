package com.other;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-21 上午10:55:10
 */
public class DebugTest {
	public static String str = "";
	public void test(){
		int x = 5;
		int y = 10;
		int z = 15;
		str = "test";
		test1(x,y);
	}
	public void test1(int x,int y){
		str = "test1";
		System.out.println(x+y);
	}
	
	public static void main(String[] args) {
		
		for(int i=0;i<5;i++){
			DebugThread t = new DebugThread("线程"+i);
			t.start();
		}
		//DebugTest test = new DebugTest();
		//test.test();
		//System.out.println("..,");
		
	}
}
