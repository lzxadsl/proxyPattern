package com.thread;

/**
 * 模拟取钱
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-11-2 下午2:20:53
 */
public class MoneyService {

	private byte[] lock = new byte[0];  //特殊的instance变量，来充当锁（创建0长度的byte速度最快）
	
	/**
	 * 下面两个方法的锁是一样的，都是针对调用该方法的对象
	 * (多线程调用时，必须针对同一个对象，synchronized才会生效)
	 */
	public synchronized void getMoney(long num){
		try {
			System.out.println("取钱中。。。");
			Thread.sleep(1000);
			System.out.println("取："+num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setMoney(long num){
		try {
			synchronized (this) {
				System.out.println("存钱中。。。");
				Thread.sleep(1000);
				System.out.println("存："+num);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void getMoney(int num){
		try {
			synchronized (lock) {
				System.out.println("取钱中，请稍等。。。");
				Thread.sleep(1000);
				System.out.println("取："+num);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
