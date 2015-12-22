package com.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池溢出
 * VM Args:-XX:PermSize=10M -XX:MaxPermSize=10M
 * jdk7之后这两个参数无效，方法区被移到了堆中
 */
public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {

      List<String> list = new ArrayList<String>();

      int i = 0;

      while (true) {
        list.add(String.valueOf(i++).intern());
      }
      
    }
}
