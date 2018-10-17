package com.zcj.test;

import java.text.DecimalFormat;

public class SalaryTest {
	public static void main(String[] args) {
		int basicSalary = 4500;// 基本工资
		double totalWorkHours = ((8 * 60 * 17 + 17 * 40) / 60); // 总工时
		int workDays = 17; // 工作天数
		int leaveDays = 0; // 请假天数
		int bookDinners = 6; // 订餐数
		int weekendThan6Hours = 1; // 周末加班超过6小时的次数

		double realTotalWorkHours = totalWorkHours - bookDinners * 0.3;
		double partOne = Math.min(realTotalWorkHours / (workDays - leaveDays) - 7.2, 1.2) / 1.2 * 0.1;
		double partTwo = Math.max((realTotalWorkHours - 8.4 * (workDays - leaveDays)) / 2.5 * 0.01, 0);
		double partThree = weekendThan6Hours * 0.03;

		DecimalFormat df = new DecimalFormat("######0.000");
		double finalPerformance = Double.valueOf(df.format(partOne + partTwo + partThree));
		System.out.println("绩效：" + df.format(partOne) + "+" + df.format(Double.valueOf(df.format(partTwo))) + "+" + df.format(partThree)
				+ "=" + df.format(finalPerformance));
		System.out.println("绩效金额：￥" + new DecimalFormat("######0.00").format(finalPerformance * basicSalary));
	}
}
