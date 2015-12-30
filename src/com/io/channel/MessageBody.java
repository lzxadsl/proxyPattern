package com.io.channel;

import java.io.Serializable;

/**
 * 消息体
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-30 下午1:04:43
 */
public class MessageBody implements Serializable{

	private static final long serialVersionUID = 7535915669400732925L;

	private String type;//消息类型
	private Object message;//传送的消息
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
