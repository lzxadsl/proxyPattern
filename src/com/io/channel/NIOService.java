package com.io.channel;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import com.basic.model.User;

/**
 * 服务端
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-29 下午2:56:38
 */
public class NIOService {

	/*缓冲区大小*/  
    private  int BLOCK = 4096;  
	private  Selector selector; 
	
	/**
	 * 构造方法，服务器初始化
	 * @param port
	 */
	public NIOService(int port){
		try {
			System.setOut(new PrintStream(new File("/home/lzx/java_log/server.log")));
			// 打开服务器套接字通道  
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			// 服务器配置为非阻塞  
			serverSocketChannel.configureBlocking(false);
			// 检索与此通道关联的服务器套接字  
			ServerSocket serverSocket = serverSocketChannel.socket();
			// 进行服务的绑定  
			serverSocket.bind(new InetSocketAddress(port));
			// 通过open()方法找到Selector  
			selector = Selector.open();
			// 注册到selector，等待连接  
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 服务监听
	 * @author LiZhiXian
	 * @version 1.0
	 * @throws IOException 
	 * @date 2015-12-29 下午3:11:56
	 */
	public void listen(){
		while(true){//轮询访问selector  
            try {
            	// 选择一组键，并且相应的通道已经打开  
				selector.select();
				// 返回此选择器的已选择键集。  
	            Set<SelectionKey> selectionKeys = selector.selectedKeys();  
	            Iterator<SelectionKey> it = selectionKeys.iterator();
	            while(it.hasNext()){
	            	SelectionKey selectionKey = it.next();  
	                it.remove(); 
	                handelRequest(selectionKey);
	            }
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}  
		}
	}
	
	/**
	 * 处理客户端发来的请求 的事件 
	 * @author LiZhiXian
	 * @version 1.0
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @date 2015-12-29 下午3:32:34
	 */
	public void handelRequest(SelectionKey selectionKey) throws IOException, ClassNotFoundException{ 
        //发送用的缓冲区
  		ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
  		//接收用的缓冲区
  		ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);
  		ServerSocketChannel server;
  		SocketChannel client;
  		int count;
  		//String receiveText = "";
  		String sendText;
  		
  		if(selectionKey.isAcceptable()){
  		    // 返回为之创建此键的通道。  
            server = (ServerSocketChannel) selectionKey.channel();  
            // 接受到此通道套接字的连接。  
            // 此方法返回的套接字通道（如果有）将处于阻塞模式。  
            client = server.accept();//等待客户端接入  
            // 配置为非阻塞  
            client.configureBlocking(false);  
            // 注册到selector，等待连接  
            client.register(selector, SelectionKey.OP_READ);  
  		}else if(selectionKey.isReadable()){
  			client = (SocketChannel) selectionKey.channel();
  			//将缓冲区清空以备下次读取  
            receivebuffer.clear();  
            //读取服务器发送来的数据到缓冲区中  
            count = client.read(receivebuffer);  
            if (count > 0) {  
            	//ByteArrayInputStream input = new ByteArrayInputStream(receivebuffer.array());
            	//ObjectInputStream objInput = new ObjectInputStream(input); 
            	//User user = (User) objInput.readObject();
            	User user = (User)MessageHandleUtil.receiveMessage(receivebuffer);
            	System.out.println("\r\n");
            	System.out.println("--------------服务端收到一个请求---------------");
            	System.out.println("用户名："+user.getUsername());
            	System.out.println("-----------------------------------------------");
            	System.out.println("\r\n");
                client.register(selector, SelectionKey.OP_WRITE);  
            }
            
  		}else if(selectionKey.isWritable()){
	  		//将缓冲区清空以备下次写入  
	        sendbuffer.clear();  
	        // 返回为之创建此键的通道。  
	        client = (SocketChannel) selectionKey.channel();  
	        sendText="IM SERVICE--";  
            MessageHandleUtil.sendMessage(client, sendbuffer, sendText);
            client.register(selector, SelectionKey.OP_READ);  
  		}
	}
	public static void main(String[] args) {
		int port = 8899;
		NIOService server = new NIOService(port);
		server.listen();
	}
}
