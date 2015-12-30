package com.io.channel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 消息收发工具类
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-30 上午11:16:51
 */
public class MessageHandleUtil {
  	
	/**
	 * 接收对象
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-30 上午11:17:29
	 * @param receiveBuffer 字节缓冲对象
	 */
	private static Object receiveObject(ByteBuffer receiveBuffer){
    	Object obj = null;
		try {
			ByteArrayInputStream input = new ByteArrayInputStream(receiveBuffer.array());
			ObjectInputStream objInput = new ObjectInputStream(input);
			obj = objInput.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
    	return obj;
	}
	
	/**
	 * 发送对象
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-30 上午11:31:21
	 * @param socketChannel 网络通道
	 * @param sendbuffer 字节缓冲区
	 * @param obj 要发送的对象
	 */
	private static void sendObject(SocketChannel socketChannel,ByteBuffer sendbuffer,Object obj){
		try {
			sendbuffer.clear(); 
			ByteArrayOutputStream bOut = new ByteArrayOutputStream(); 
			ObjectOutputStream output = new ObjectOutputStream(bOut);
			output.writeObject(obj);
	        output.flush();
	        //向缓冲区中输入数据  
	        sendbuffer.put(bOut.toByteArray()); 
	        //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位  
	        sendbuffer.flip();  
	        //输出到通道  
	        socketChannel.write(sendbuffer);  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送文本信息
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-30 上午11:40:58
	 */
	public static void sendText(SocketChannel socketChannel,ByteBuffer sendbuffer,String text){
		try {
			sendbuffer.clear();  
            sendbuffer.put(text.getBytes());  
            sendbuffer.flip();  
            socketChannel.write(sendbuffer); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送标准的消息
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-30 下午1:10:42
	 * @param socketChannel 网络通道
	 * @param sendbuffer 字节缓冲区
	 * @param message 要发送的消息
	 */
	public static void sendMessage(SocketChannel socketChannel,ByteBuffer sendbuffer,Object message){
		MessageBody body = new MessageBody();
		body.setType("");
		body.setMessage(message);
		sendObject(socketChannel,sendbuffer,body);
	}
	
	/**
	 * 接收标准的消息
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-30 下午1:17:51
	 * @param receivebuffer
	 */
	public static Object receiveMessage(ByteBuffer receivebuffer){
		MessageBody body = (MessageBody) receiveObject(receivebuffer);
		return body.getMessage();
	}
	
	public static void main(String[] args) {
		//MessageHandleUtil.test(obj);
	}
}
