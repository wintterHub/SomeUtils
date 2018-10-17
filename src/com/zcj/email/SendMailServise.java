package com.zcj.email;

/**
 * @author ZhaoChangjin
 * @date 2017年12月11日
 */
public class SendMailServise {

	/**
	 * 发送电子邮件到指定的信箱
	 * 
	 * @param title
	 *            邮件标题
	 * @param mailbody
	 *            邮件内容(可以是网页)
	 * @param sendTo
	 *            收件人邮箱地址
	 * @param from
	 *            发送人邮箱地址
	 * @param passwd
	 *            密码
	 * @param sendStmp
	 *            发送邮件的smtp
	 */
	public static boolean send(String title, String mailbody, String sendTo, String from, String password, String sendStmp) {

		// 指明让那个smtp转发
		SendMail themail = new SendMail(sendStmp);

		// 校验身份
		themail.setNeedAuth(true);

		// 邮件标题
		if (themail.setSubject(title) == false)
			return false;
		// 将要发送的 内容放入邮件体
		if (themail.setBody(mailbody) == false)
			return false;

		// 发送到哪里
		if (themail.setTo(sendTo) == false)
			return false;

		// 谁发送的
		if (themail.setFrom(from) == false)
			return false;

		// 添加附件
		// if(themail.addFileAffix("e:\\butterfly.gif") == false) return;

		// 将邮件用户名和 密码 放入邮件体
		themail.setNamePass(from, password);

		// 发送
		if (themail.sendout() == false)
			return false;
		return true;
	}
}
