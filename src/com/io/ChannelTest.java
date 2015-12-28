package com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件管道测试
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-25 下午4:49:35
 */
public class ChannelTest {

	private final String path;
	private final static int BUF_SIZE = 1024;
	
	public ChannelTest(){
		ChannelTest.class.getClassLoader();
		URL url = ClassLoader.getSystemResource("remark.log");
		this.path = url.getPath();
		System.out.println(path);
	}
			
	/**
	 * 写入文件
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-25 下午4:50:03
	 */
	public void writeToFile(){
		FileOutputStream out = null;
		FileChannel channel = null;
		try {
			File file = new File(path);
			//file.setWritable(true);
			out = new FileOutputStream(file);
			channel = out.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);
			buf.put("写入了一段话，你好啊1。。".getBytes());
			out.flush();
			buf.flip();
			channel.write(buf);
			//buf.compact();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				channel.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("写入成功。。。。。。");
		
	}
	/**
	 * 读取文件
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-25 下午5:00:33
	 */
	public void readToFile(){
		FileInputStream in;
		try {
			in = new FileInputStream(new File(path));
			FileChannel channel = in.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);
			int byteread = channel.read(buf);
			//System.out.println(byteread+":"+(int)channel.size());
			buf.flip();
			String str = new String(buf.array(),0,byteread);
			System.out.println("读取文件内容。。。。。。。。。。");
			System.out.println(str);
			/*while(byteread != -1){
				
			}*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		ChannelTest test = new ChannelTest();
		test.writeToFile();
		test.readToFile();
	}
	
}
