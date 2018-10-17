package com.zcj.test;

public class Performance {

	public static void main(String[] args) {
		System.out.println(test(8.4f * 22, 22, 0, 0));
		System.out.println(6.90 + 7.22 + 8.92 + 8.18 + 8.18 + 7.95 + 7.42 + 9.55 + 8.22 + 8.20 + 8.38 + 8.30 + 8.43 + 9.33 + 9.45 + 8.20
				+ 8.03 + 8.17 + 8.25 + 8.17 + 8.07 + 8.95);
		System.out.println(9.75 + 9.03 + 9.13 + 8.33 + 8.40 + 8.32 + 8.65 + 9.53 + 7.00 + 7.83 + 9.32 + 8.03 + 9.08 + 9.07 + 7.73 + 9.82
				+ 9.82 + 2.67 + 1.52 + 7.45 + 8.68 + 9.35 + 7.95);
	}

	/**
	 * @param total 总时长
	 * @param workdays 本月工作天数
	 * @param leave 请假天数
	 * @param overtime 超过6小时的周末加班天数
	 * @return
	 */
	public static float test(float total, int workdays, int leave, int overtime) {
		float f = (float) (((Math.min(total / (workdays - leave) - 7.2, 1.2)) / 1.2 * 0.1
				+ (Math.max((total - 8.4 * (workdays - leave)) / 2.5 * 0.01, 0)) + overtime * 0.03) * (workdays - leave) / workdays);
		return f;
	}

}
