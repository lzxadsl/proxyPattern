package com.string;

import java.lang.reflect.Method;

/**
 * String 测试
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-11-4 下午2:48:52
 */
public class StringTest {

	static{
		System.out.println("我是被加载到内存时就执行");
	}
	public StringTest(){
		System.out.println("我是在对象被创建时执行........");
	}
	
	public void strAppend(){
		long start = System.currentTimeMillis();
		String str = "";
		for(int i=0; i<10000 ;i++){
			str += "str";
		}
		long end = System.currentTimeMillis();
		System.out.println("总耗时："+(end-start));
	}
	
	public void strBufAppend(){
		long start = System.currentTimeMillis();
		StringBuffer strBuf = new StringBuffer("");
		for(int i=0; i<10000 ;i++){
			strBuf.append("str"+i);
		}
		long end = System.currentTimeMillis();
		System.out.println("总耗时："+(end-start));
	}
	
	public static void main(String[] args) throws Exception {
		//StringTest st = new StringTest();
		//st.strBufAppend();
		Class clazz = Class.forName("com.string.StringTest");
	}
}
