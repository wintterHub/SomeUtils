package com.zcj.test;

import java.math.BigInteger;

/**
 * @author zhaochangjin
 * @mail zhaochangjin@zving.com
 * @date 2017Äê4ÔÂ11ÈÕ
 */
public class Fibonacci {

	public static int fibonacci1(int num) {
		if (num >= 0) {
			if (num == 0) {
				return num;
			} else if (num == 1) {
				return num;
			} else {
				return fibonacci1(num - 1) + fibonacci1(num - 2);
			}
		}
		return 0;
	}

	public static int fibonacci2(int num) {
		if (num >= 0) {
			if (num == 0) {
				return num;
			} else if (num == 1) {
				return num;
			} else {
				int a = 0;
				int b = 1;
				for (int i = 1; i < num; i++) {
					if (i % 2 == 0) {
						b = a + b;
					} else {
						a = a + b;
					}
				}
				return a > b ? a : b;
			}
		}
		return 0;
	}

	public static BigInteger fibonacci3(int num) {
		if (num >= 0) {
			if (num == 0) {
				return new BigInteger("0");
			} else if (num == 1) {
				return new BigInteger("1");
			} else {
				BigInteger a = new BigInteger("0");
				BigInteger b = new BigInteger("1");
				for (int i = 1; i < num; i++) {
					if (i % 2 == 0) {
						b = a.add(b);
					} else {
						a = a.add(b);
					}
				}
				return (a.compareTo(b) > -1) ? a : b;
			}
		}
		return new BigInteger("0");
	}

	public static void main(String[] args) {
		int fibonacci1 = fibonacci1(15);
		System.out.println(fibonacci1);

		int fibonacci2 = fibonacci2(75);
		System.out.println(fibonacci2);

		BigInteger fibonacci3 = fibonacci3(1500);
		System.out.println(fibonacci3);
	}

}
