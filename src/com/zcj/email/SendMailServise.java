package com.zcj.email;

/**
 * @author ZhaoChangjin
 * @date 2017��12��11��
 */
public class SendMailServise {

	/**
	 * ���͵����ʼ���ָ��������
	 * 
	 * @param title
	 *            �ʼ�����
	 * @param mailbody
	 *            �ʼ�����(��������ҳ)
	 * @param sendTo
	 *            �ռ��������ַ
	 * @param from
	 *            �����������ַ
	 * @param passwd
	 *            ����
	 * @param sendStmp
	 *            �����ʼ���smtp
	 */
	public static boolean send(String title, String mailbody, String sendTo, String from, String password, String sendStmp) {

		// ָ�����Ǹ�smtpת��
		SendMail themail = new SendMail(sendStmp);

		// У�����
		themail.setNeedAuth(true);

		// �ʼ�����
		if (themail.setSubject(title) == false)
			return false;
		// ��Ҫ���͵� ���ݷ����ʼ���
		if (themail.setBody(mailbody) == false)
			return false;

		// ���͵�����
		if (themail.setTo(sendTo) == false)
			return false;

		// ˭���͵�
		if (themail.setFrom(from) == false)
			return false;

		// ��Ӹ���
		// if(themail.addFileAffix("e:\\butterfly.gif") == false) return;

		// ���ʼ��û����� ���� �����ʼ���
		themail.setNamePass(from, password);

		// ����
		if (themail.sendout() == false)
			return false;
		return true;
	}
}
