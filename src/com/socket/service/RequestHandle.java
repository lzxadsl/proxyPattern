package com.socket.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 请求处理
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-23 上午10:26:14
 */
public class RequestHandle implements Runnable{

	private final Socket socket;
	
	public RequestHandle(Socket socket){
		this.socket = socket;
	}
	
	/**
	 * 
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-23 上午10:28:14
	 */
	@Override
	public void run() {
		try {
			handleSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理逻辑
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-23 上午11:02:30
	 */
	private void handleSocket() throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String msg;
		while(true){
			msg = reader.readLine();
			if(msg != null){
				if(!"stop".equals(msg)){
					System.out.println("----------服务的接收到一个请求----------");
					System.out.println(msg);
				}else{
					break;
				}
			}
		}
		reader.close();
		socket.close();
	}
}
