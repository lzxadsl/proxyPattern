/**
 * 
 */
package com.tank.service;

import java.util.Random;

import com.proxy.interfaces.Moving;

/**
 * 坦克类
 * @author 李智贤
 * 2015-10-20
 */
public class Tank implements Moving{

	@Override
	public void move(){
		try {
			System.out.println("坦克开始移动....");
			Thread.sleep(new Random().nextInt(10000));//睡眠，代表坦克正在移动中
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
