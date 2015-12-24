package com.socket.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import com.basic.model.User;

/**
 * 客户端
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-23 上午11:03:43
 */
public class Client {

	private String ip = "127.0.0.1";
	private int port = 8899;
	
	/**
	 * 启动客户端
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-23 上午11:04:34
	 */
	public void start(){
		String msg = "888";
		Scanner scan = new Scanner(System.in);
		try {
			Socket client = new Socket(ip,port);
			client.setKeepAlive(true);
			BufferedWriter write = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			while(!"stop".equals(msg)){
				msg = scan.nextLine();
				write.write(msg);
				System.out.println("客户端发送了一条消息");
			}
			write.flush();
			write.close();
			client.close();
			System.out.println("客户端关闭。。。。。");
		} catch (IOException e) {
			System.out.println("客户端启动失败.....");
			e.printStackTrace();
		}
	}
	
	public void sendObj(){
		ObjectOutputStream out;
		User user = new User();
		user.setId(1);
		user.setPassword("123456");
		user.setUsername("lzx");
		try {
			Socket client = new Socket(ip,port);
			out = new ObjectOutputStream(client.getOutputStream());
			out.writeObject(user);
			out.flush();
			out.close();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Client c = new Client();
		//c.start();
		c.sendObj();
	}
}
