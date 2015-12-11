package com.thread;

import com.thread.model.User;


/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-10 下午5:22:45
 */
public class ConnThreadLocation {

	// ①使用ThreadLocal保存Connection变量  
    private static ThreadLocal<User> connThreadLocal = new ThreadLocal<User>(){
    	protected User initialValue() {//设置初始值
    		User user = new User();  
    		user.setName("lzx");
    		return user;
    	};
    };  
  
    public User getUser() {  
        // ②如果connThreadLocal没有本线程对应的Connection创建一个新的Connection，  
        // 并将其保存到线程本地变量中。  
        if (connThreadLocal.get() == null) {  
        	System.out.println("创建。。。。");
        	User user = new User();  
        	user.setName(Thread.currentThread().getName());
            connThreadLocal.set(user);  
            return user;  
        } else {  
            return connThreadLocal.get();// ③直接返回线程本地变量  
        }  
    }  
}
