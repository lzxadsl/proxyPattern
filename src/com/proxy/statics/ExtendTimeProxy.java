/**
 * 
 */
package com.proxy.statics;

import com.tank.service.Tank;

/**
 * @author 李智贤
 * 2015-10-20
 */
public class ExtendTimeProxy extends Tank{
	
	public ExtendTimeProxy(Tank tank){
		this.t = tank;
	}
	Tank t;
	public void move() {
		System.out.println("坦克记录日志开始...");
		t.move();//被代理方法
		System.out.println("坦克记录日志结束...");
	}
}
