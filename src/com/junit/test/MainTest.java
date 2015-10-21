/**
 * 
 */
package com.junit.test;

import com.proxy.interfaces.Moving;
import com.proxy.statics.ExtendLogProxy;
import com.proxy.statics.ExtendTimeProxy;
import com.proxy.statics.TankLogProxy;
import com.proxy.statics.TankTimeProxy;
import com.tank.service.Tank;

/**
 * @author 李智贤
 * 2015-10-20
 */
public class MainTest {
	/**
	 * 采用接口形势
	 */
	public static void testMove(){
		Moving t = new Tank();
		//先记录时间，后记录日志，如果要对调只需把下面两行对换一下
		Moving ttp = new TankTimeProxy(t);
		Moving tlp = new TankLogProxy(ttp);
		tlp.move();
	}
	
	/**
	 * 采用继承形势
	 */
	public static void testMove1(){
		Tank t = new Tank();
		//先记录时间，后记录日志，如果要对调只需把下面两行对换一下
		ExtendLogProxy elp = new ExtendLogProxy(t);
		ExtendTimeProxy etp = new ExtendTimeProxy(elp);
		etp.move();
	}
	
	public static void main(String[] args) {
		testMove();
	}

}
