/**
 * 
 */
package com.proxy.statics;

import com.proxy.interfaces.Moving;

/**
 * 坦克日志代理类，静态代理
 * 采用聚合形势，一个类里面包含了另外一个类（Moving）
 * @author 李智贤
 * 2015-10-20
 */
public class TankLogProxy implements Moving{
	Moving t;
	public TankLogProxy(Moving m){
		this.t = m;
	}
	
	/**
	 * 在坦克移动方法前后加上日志
	 */
	@Override
	public void move() {
		System.out.println("坦克记录日志开始...");
		t.move();//被代理方法
		System.out.println("坦克记录日志结束...");
	}
	
}
