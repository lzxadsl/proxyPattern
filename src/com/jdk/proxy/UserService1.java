/**
 * 
 */
package com.jdk.proxy;

/**
 * @author 李智贤
 * 2015-11-8
 */
public class UserService1 implements IUserService {

	@Override
	public void addUser() {
		System.out.println("这是新增用户操作...");
	}

}
