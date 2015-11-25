package com.thread;

import java.util.Scanner;

public class ThreadExtend extends Thread{
	
	public void run(){
		int i = 0;
		synchronized(this){
			try {
				while(true){
					//System.out.println("开始工作啦..."+i);
					//sleep(1000);
					System.out.println("开始工作啦..."+i);
					this.wait();
					i++;
				}
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			System.out.println("等待输入0...");
			Scanner s = new Scanner(System.in);
			if(Integer.valueOf(s.next())==0){
				this.notify();
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadExtend t = new ThreadExtend();
		t.start();
	}
}
