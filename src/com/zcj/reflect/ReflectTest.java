package com.zcj.reflect;

import com.zcj.util.MacUtil;

public class ReflectTest {

	public static void main(String[] args) {
		MacUtil macUtil = new MacUtil();
		Class<?> class1 = macUtil.getClass();
		try {
			Object user = class1.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		String name = macUtil.getClass().getName();
		System.out.println(name);
	}

}
