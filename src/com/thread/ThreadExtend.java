package com.thread;

import java.sql.SQLException;


public class ThreadExtend extends Thread{
	ConnThreadLocation conn;
	
	public ThreadExtend(ConnThreadLocation con){
		this.conn = con;
	}
	
	public void run(){
		try {
			conn.addTopic();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(getName()+"--");
	}
	
	public static void main(String[] args){
		ConnThreadLocation con = new ConnThreadLocation();
		Thread t1 = new ThreadExtend(con);
		t1.start();
		/*for(int i=0;i<10;i++){
			Thread t1 = new ThreadExtend(con);
			t1.start();
		}*/
	}
}
