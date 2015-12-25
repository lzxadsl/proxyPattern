package com.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 随机访问文件的读取和写入
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-25 下午3:39:16
 */
public class TestRandomAccessFile {

	public static void main(String[] args) {
		try {
			RandomAccessFile rf = new RandomAccessFile("log.log","rw");
			//rf.writeInt(1000);
			//rf.writeDouble(2000);
			rf.writeUTF("你好啊..a");
			//rf.seek(10);
			//rf.write("你好啊..a".getBytes("gb2312"));
			rf.close();
			rf = new RandomAccessFile("log.log","r");
			ByteBuffer buf = ByteBuffer.allocate(1024);
			FileChannel channel = rf.getChannel();
			//channel.force(true);
			int bytesRead  = channel.read(buf);
			while(bytesRead  != -1){
				buf.flip();  //make buffer ready for read  
			    while(buf.hasRemaining()){  
			    	//char c = (char) buf.get();
			    	String str = new String(buf.array(),0,bytesRead);
			    	System.out.print(str); // read 1 byte at a time 
			    } 
			    buf.clear();
			    bytesRead  = rf.getChannel().read(buf);
			}
			rf.close();
			//System.out.println(rf.readUTF());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
