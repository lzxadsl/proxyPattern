package com.io.channel;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 客户端
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-29 下午3:38:40
 */
public class NIOClient {

	/*缓冲区大小*/  
    private  int BLOCK = 4096; 
	//选择器
	private Selector selector;
	
	/**
	 * 构造函数，初始化连接
	 * @param port
	 */
	public NIOClient(int port){
		try {
			System.setOut(new PrintStream(new File("/home/lzx/java_log/client.log")));
			// 打开socket通道  
			SocketChannel clientChannel = SocketChannel.open();
			// 设置为非阻塞方式  
			clientChannel.configureBlocking(false);
			// 打开选择器  
			selector = Selector.open();
			// 注册连接服务端socket动作  
			clientChannel.register(selector,SelectionKey.OP_CONNECT);
			// 连接  
			clientChannel.connect(new InetSocketAddress(port)); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 客户端发送请求
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-29 下午3:45:33
	 */
	public void sendRequest(){
		Set<SelectionKey> selectionKeys = null;
		SelectionKey selectionKey = null;
		SocketChannel client = null;
		//发送用的缓冲区
		ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
		//接收用的缓冲区
		ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);
		String receiveText;  
        String sendText;
        int flag = 0;
		while(true){
			try {
				selector.select();
				//返回此选择器的已选择键集。  
	            selectionKeys = selector.selectedKeys();  
	            Iterator<SelectionKey> it = selectionKeys.iterator();
	            while(it.hasNext()){
	            	selectionKey = it.next();  
	            	if(selectionKey.isConnectable()){//是否处于连接状态
	            		client = (SocketChannel) selectionKey.channel();
	            		// 判断此通道上是否正在进行连接操作。  
	                    // 完成套接字通道的连接过程。  
	                    if (client.isConnectionPending()) {  
	                        client.finishConnect();  
	                        System.out.println("完成连接!");  
	                        sendbuffer.clear();  
	                        sendbuffer.put("Hello,Server".getBytes());  
	                        sendbuffer.flip();  
	                        client.write(sendbuffer);  
	                    }  
	                    client.register(selector, SelectionKey.OP_READ); 
	            	}else if(selectionKey.isReadable()){//是否处于读取状态
	            		client = (SocketChannel) selectionKey.channel();  
	                    //将缓冲区清空以备下次读取  
	                    receivebuffer.clear();  
	                    //读取服务器发送来的数据到缓冲区中  
	                    int count = client.read(receivebuffer);  
	                    if(count > 0){  
	                        receiveText = new String(receivebuffer.array(),0,count);  
	                        System.out.println("AndyClient接收数据--:"+receiveText);  
	                        client.register(selector, SelectionKey.OP_WRITE);  
	                    }  
	            	}else if(selectionKey.isWritable()){//是否处于写入状态
	            		sendbuffer.clear();  
	                    client = (SocketChannel) selectionKey.channel();  
	                    sendText = "AndyClient" + (flag++);  
	                    sendbuffer.put(sendText.getBytes()); 
	                    //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位  
	                    sendbuffer.flip();  
	                    client.write(sendbuffer);  
	                    System.out.println("AndyClient发送数据--："+sendText);  
	                    client.register(selector, SelectionKey.OP_READ);  
	            	}
	            }
	            selectionKeys.clear(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		int port = 8899;
		NIOClient client = new NIOClient(port);
		client.sendRequest();
	}
}
