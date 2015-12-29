package com.other;

import com.basic.model.User;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-28 下午1:28:49
 */
public class StaticMethodTest {
	
	public StaticMethodTest(){
		System.out.println("构造函数执行 x:"+(x++)+" y:"+y++);
	}
	static{
		System.out.println("静态代码块1执行");
	}
	static User user;{
		System.out.println("生明一个user");
	}
	int y = 0;{
		System.out.println("初始化变量y");
	}
	static int x = 0;{
		System.out.println("初始化静态变量x");
	}
	static{
		System.out.println("静态代码块2执行");
	}
	final static int j = 0;{
		System.out.println("final 静态变量");
	}
	private static class nbl{
		static{
			System.out.println("我是内部类");
		}
	}{
		System.out.println("内部静态类初始化");
	}
	
	public static void test(){
		System.out.println("我是静态方法");
	}{
		System.out.println("静态方法初始化");
	}

	/**
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-28 下午1:28:49
	 */
	public static void main(String[] args) {
		//StaticMethodTest test = new StaticMethodTest();
		//StaticMethodTest[] array = new StaticMethodTest[10];//定义类数组不会引起类初始化
		User z = StaticMethodTest.user;
		/*try {
			Class.forName("com.other.StaticMethodTest");//加载类到内存
			System.out.println("---------------类加载完毕--------------");
			new StaticMethodTest();
			System.out.println("-----------------------------");
			new StaticMethodTest();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/

	}

}
