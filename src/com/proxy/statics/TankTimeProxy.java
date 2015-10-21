/**
 * 
 */
package com.proxy.statics;

import com.proxy.interfaces.Moving;

/**
 * 坦克时间代理类，静态代理
 * 采用聚合形势，一个类里面包含了另外一个类（Moving）
 * @author 李智贤
 * 2015-10-20
 */
public class TankTimeProxy implements Moving{
	Moving t;
	public TankTimeProxy(Moving m){
		this.t = m;
	}
	
	/**
	 * 计算坦克移动方法运行多长时间
	 */
	@Override
	public void move() {
		long start = System.currentTimeMillis();
		t.move();//被代理方法
		long end = System.currentTimeMillis();
		System.out.println("运行时间:"+(end - start));
	}

	
}
