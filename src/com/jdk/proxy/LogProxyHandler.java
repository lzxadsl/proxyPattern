/**
 * 
 */
package com.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 日志记录
 * @author 李智贤
 * 2015-11-8
 */
public class LogProxyHandler implements InvocationHandler{
	
	//目标对象 (被代理对象)  
    private Object target;  
      
    /** 
     * 构造方法 
     * @param target 目标对象  
     */  
    public LogProxyHandler(Object target) {  
        super();  
        this.target = target;  
    }  
	/**
	 * @param proxy 代理对象
	 * @param method 被代理类中的方法
	 * @param args 方法对应的参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("调用前添加日志..");
		 Object result = method.invoke(target,args);
		System.out.println("调用后添加日志..");
		return result;
	}

}
