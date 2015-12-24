/**
 * 
 */
package com.junit.test;

import java.lang.reflect.Proxy;

import com.jdk.proxy.IUserService;
import com.jdk.proxy.LogProxyHandler;
import com.jdk.proxy.UserService;
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
	
	/**
	 * jdk 动态代理的使用
	 */
	public static void testJdkProxy(){
		//要被代理的接口
		IUserService userService = new UserService();
		//代理处理器
		LogProxyHandler handler = new LogProxyHandler(userService);
		/**
		 * 第一个参数是类加载器
		 * 第二个参数是被代理对象接口(包括实现了该接口的所有类)
		 * 第三个参数是处理逻辑（对被代理对象所做的处理逻辑）
		 * Proxy.newProxyInstance 其实是去遍历 被代理的接口中的每一个方法，然后对他们进行handler处理
		 */
		IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), userService.getClass().getInterfaces(),handler);
		proxy.addUser();//调用改方法时LogProxyHandler的处理逻辑就会被运用到该方法上
	}
	
	public static void main(String[] args) {
		//testMove();
		testJdkProxy();
		ProxyGeneratorUtils.writeProxyClassToHardDisk("/home/lzx/$Proxy11.class"); 
	}
}
