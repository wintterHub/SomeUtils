package com.zcj.email;

public class test {

	public static void main(String[] args) {
		SendMailServise.send(//
				"您的密码已重置", //
				"随机密码为：" + 123456, //
				"1505322835@qq.com", //
				"17096701945@163.com", //
				"zhaochangjin28", //
				"smtp.163.com");
	}

}
