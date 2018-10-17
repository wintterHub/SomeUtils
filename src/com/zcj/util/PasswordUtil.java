package com.zcj.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ZhaoChangjin
 * @date 2017��9��9��
 */
public class PasswordUtil {

	/**
	 * �������
	 * 
	 * @param password ��Ҫ���ܵ���������
	 * @return
	 */
	public static String generate(String password) {
		String salt = getSalt();
		password = md5(password + salt);
		char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			cs[i] = password.charAt(i / 3 * 2);
			char c = salt.charAt(i / 3);
			cs[(i + 1)] = c;
			cs[(i + 2)] = password.charAt(i / 3 * 2 + 1);
		}
		return new String(cs);
	}

	/**
	 * ������֤
	 * 
	 * @param inputPwd ���������
	 * @param dbPwd ���ݿⱣ�������
	 * @return
	 */
	public static boolean validate(String inputPwd, String dbPwd) {
		String salt = getSalt(dbPwd);
		inputPwd = md5(inputPwd + salt);
		char[] cs = new char[48];
		for (int i = 0; i < 48; i += 3) {
			cs[i] = inputPwd.charAt(i / 3 * 2);
			char c = salt.charAt(i / 3);
			cs[(i + 1)] = c;
			cs[(i + 2)] = inputPwd.charAt(i / 3 * 2 + 1);
		}
		String newPwd = new String(cs);
		return newPwd.equals(dbPwd);
	}

	/**
	 * ��ȡ�����е���
	 * 
	 * @param password
	 * @return
	 */
	private static String getSalt(String password) {
		char[] salt = new char[16];
		for (int i = 0; i < 48; i += 3) {
			salt[i / 3] = password.charAt(i + 1);
		}
		return new String(salt);
	}

	/**
	 * ���������
	 * 
	 * @return
	 */
	private static String getSalt() {
		return leftPad(getRandomNum(0, 99999999) + "", '0', 8) + leftPad(getRandomNum(0, 99999999) + "", '0', 8);
	}

	/**
	 * ����
	 * 
	 * @param password
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String desalt(String password) {
		char[] cs = new char[32];
		for (int i = 0; i < 48; i += 3) {
			cs[i / 3 * 2] = password.charAt(i);
			cs[i / 3 * 2 + 1] = password.charAt(i + 2);
		}
		return new String(cs);
	}

	/**
	 * MD5����
	 * 
	 * @param string
	 * @return
	 */
	private static String md5(String string) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	/**
	 * ��һ�ַ�������������ָ���ַ���ʹ�䳤�ȴﵽָ������
	 * 
	 * @param srcString
	 * @param c
	 * @param length
	 * @return
	 */
	private static String leftPad(String srcString, char c, int length) {
		if (srcString == null) {
			srcString = "";
		}
		int tLen = srcString.length();
		int i, iMax;
		if (tLen >= length) {
			return srcString;
		}
		iMax = length - tLen;
		StringBuilder sb = new StringBuilder();
		for (i = 0; i < iMax; i++) {
			sb.append(c);
		}
		sb.append(srcString);
		return sb.toString();
	}

	/**
	 * ȡһ���������
	 * 
	 * @param offset ��ʼֵ(����)
	 * @param end ����ֵ(������)
	 * @return
	 */
	private static int getRandomNum(int offset, int end) {
		return (int) (Math.random() * (end - offset) + offset);
	}
}
