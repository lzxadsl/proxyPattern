/**
 * 
 */
package com.proxy.statics;

import com.tank.service.Tank;

/**
 * @author 李智贤
 * 2015-10-20
 */
public class ExtendLogProxy extends Tank{
	
	public ExtendLogProxy(Tank tank){
		this.t = tank;
	}
	
	Tank t;
	public void move() {
		long start = System.currentTimeMillis();
		t.move();//被代理方法
		long end = System.currentTimeMillis();
		System.out.println("运行时间:"+(end - start));
	}
}
