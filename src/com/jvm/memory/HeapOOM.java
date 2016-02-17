package com.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出测试
 * VM Args:-Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 *
 */
public class HeapOOM {

	public static void main(String[] args) {
		
		List<OOMObject> list = new ArrayList<OOMObject>();
	      
	      while(true){
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        list.add(new OOMObject());
	        new OOMObject();
	      }
	}

}
