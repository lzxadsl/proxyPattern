/**
 * 
 */
package com.jdk.proxy;

/**
 * @author 李智贤
 * 2015-11-8
 */
public class UserService implements IUserService,IUserService1 {

	@Override
	public void addUser() {
		System.out.println("这是新增用户操作...");
	}

	@Override
	public void addUser1() {
		System.out.println("这是新增用户操作1...");
	}

}
