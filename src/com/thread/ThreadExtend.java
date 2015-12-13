package com.thread;


public class ThreadExtend extends Thread{
	ConnThreadLocation conn;
	//User conn;
	public ThreadExtend(ConnThreadLocation con,String name){
		super(name);
		this.conn = con;
	}
	
	public void run(){
		try {
			if("2".equals(getName())){
				synchronized (conn) {
					System.out.println(getName()+"线程被停止");
					conn.wait();
				}
			}
			else if("3".equals(getName())){
				synchronized (conn) {
					throw new RuntimeException(getName()+"线程抛出异常....");
				}
			}
			else if("8".equals(getName())){
				synchronized (conn) {
					System.out.println("等待中的线程被唤醒。。。。。");
					conn.notify();
				}
			}
			System.out.println(getName()+"线程创建。。。。。");
			//User user = conn.getUser();
			//System.out.println(user.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		ConnThreadLocation con = new ConnThreadLocation();
		//User con = new User();  
		for(int i=0;i<10;i++){
			Thread t1 = new ThreadExtend(con,String.valueOf(i));
			t1.start();
		}
		//System.out.println("-----------:"+con.getUser().getName());
	}
}
