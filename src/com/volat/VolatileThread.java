package com.volat;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-21 下午3:56:16
 */
public class VolatileThread extends Thread{

	private final String name;
	private final VolatileExample example;
	public VolatileThread(String name,VolatileExample example){
		super(name);
		this.name = name;
		this.example = example;
	}
	
	/**
	 * 
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-21 下午3:56:24
	 */
	@Override
	public void run() {
		if("read".equals(name)){
			System.out.println("线程："+getName()+"进行读操作");
			example.read();
		}
		else{
			System.out.println("线程："+getName()+"进行写操作");
			example.write();
		}
	}
}
