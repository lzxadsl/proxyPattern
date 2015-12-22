package com.jvm.memory;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 方法区溢出
 * VM Args:-XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class JavaMethodAreaOOM {

	 public static void main(String[] args) {
		 while (true) {
			 Enhancer enhancer = new Enhancer();
			 enhancer.setSuperclass(OOMObject.class);
			 enhancer.setUseCache(false);
			 enhancer.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object object, Method method, Object[] args,
						MethodProxy proxy) throws Throwable {
					return proxy.invokeSuper(object, args);
				}
			 });
			 enhancer.create();
		 }

    }

}
