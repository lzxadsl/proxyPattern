package com.socket.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.basic.model.User;

/**
 * 服务端
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-23 上午10:19:48
 */
public class Server {
	private int port = 8899;
	/**
	 * 启动服务端
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-23 上午10:21:05
	 */
	public void start(){
		
		try {
			ServerSocket server = new ServerSocket(port);
			while(true){//允许多个客户端接入
				Socket socket = server.accept();//等待客户端接入
				socket.setKeepAlive(true);
				System.out.println("有个客户端请求连接...");
				new Thread(new RequestHandle(socket)).start();
			}
		} catch (IOException e) {
			System.out.println("服务端启动失败.....");
			e.printStackTrace();
		}
	}
	
	public void receiveObj(){
		ServerSocket server = null;
		Socket socket = null;
		ObjectInputStream obj = null;
		try {
			server = new ServerSocket(port);
			socket = server.accept();
			obj = new ObjectInputStream(socket.getInputStream());
			User user = (User) obj.readObject();
			System.out.println(user.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				obj.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		Server s = new Server();
		//s.start();
		s.receiveObj();
	}
}
