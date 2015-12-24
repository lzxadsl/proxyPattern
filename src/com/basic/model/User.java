package com.basic.model;

import java.io.Serializable;

/**
 * 用户实体类
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-23 上午10:11:52
 */
public class User implements Serializable{

	private static final long serialVersionUID = -5832971542838448442L;
	
	private int id;
	private String username;
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
