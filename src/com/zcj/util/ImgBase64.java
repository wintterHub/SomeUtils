package com.zcj.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author ZhaoChangjin
 * @date 2017��8��18��
 */
public class ImgBase64 {

	/**
	 * ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
	 * 
	 * @param imgFile ͼƬ·��
	 * @return
	 */
	public static String getImageStr(File imgFile) {
		InputStream in = null;
		byte[] data = null;
		// ��ȡͼƬ�ֽ�����
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ���ֽ�����Base64����
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// ����Base64��������ֽ������ַ���
	}

	/**
	 * ��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��
	 * 
	 * @param imgFile ͼƬ·��
	 * @return
	 */
	public static String getImageStr(String imgFile) {
		return getImageStr(new File(imgFile));
	}

	/**
	 * base64�ַ���ת����ͼƬ
	 * 
	 * @param imgBase64 ͼƬ��Base64����
	 * @param imgFilePath ͼƬ�����·��
	 * @return
	 */
	public static boolean generateImage(String imgBase64, File imgFile) {
		if (imgBase64 == null)
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(imgBase64);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(imgFile);
			out.write(b);
			out.flush();
			if (out != null) {
				out.close();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * base64�ַ���ת����ͼƬ
	 * 
	 * @param imgBase64 ͼƬ��Base64����
	 * @param imgFilePath ͼƬ�����·��
	 * @return
	 */
	public static boolean generateImage(String imgBase64, String imgFile) {
		return generateImage(imgBase64, new File(imgFile));
	}

}
