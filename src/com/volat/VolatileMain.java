package com.volat;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-21 下午3:58:49
 */
public class VolatileMain {

	public static void main(String[] args) {
		/*for(int i=1;i<7;i++){
			Thread t = null;
			if(i%2==0){
				t = new VolatileThread("read");
			}else{
				t = new VolatileThread("write");
			}
			t.start();
		}*/
		VolatileExample example = new VolatileExample();
		Thread t2 = new VolatileThread("write",example);
		Thread t1 = new VolatileThread("read",example);
		t2.start();
		t1.start();
	}
}
