package com.volat;

/**
 * 多线程volatile测试
 * volatile：变量修饰符，半同步，具有可见性但无原子性，效率比synchronized来得高
 * write 和 read 分别让不同的线程来进行调用，但传入线程中的VolatileExample对象必须是同一个
 * 原子性：即一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行
 * 可见性是指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-21 下午3:48:06
 */
public class VolatileExample {

	private volatile boolean flag;//volatile 保证其他线程能第一时间获得flag的最新值
	private String name = "";
	//private final static Object obj = new Object();
	public void write(){
		/*synchronized (obj) {
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		name = "lzx";
		flag = true;//标志写入完成
	}
	
	public void read(){
		boolean read = flag;
		while(!flag){//如果还没有先写入则不进行读操作，没有使用volatile时，这步经常无法读到最新值，使用了之后他会先去刷新flag的值
			System.out.println("空：");
		}
		System.out.println("读取用户名称："+name);
	}
}
