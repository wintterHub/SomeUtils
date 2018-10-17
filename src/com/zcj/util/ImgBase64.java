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
 * @date 2017年8月18日
 */
public class ImgBase64 {

	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 
	 * @param imgFile 图片路径
	 * @return
	 */
	public static String getImageStr(File imgFile) {
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	 * 
	 * @param imgFile 图片路径
	 * @return
	 */
	public static String getImageStr(String imgFile) {
		return getImageStr(new File(imgFile));
	}

	/**
	 * base64字符串转化成图片
	 * 
	 * @param imgBase64 图片的Base64编码
	 * @param imgFilePath 图片保存的路径
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
	 * base64字符串转化成图片
	 * 
	 * @param imgBase64 图片的Base64编码
	 * @param imgFilePath 图片保存的路径
	 * @return
	 */
	public static boolean generateImage(String imgBase64, String imgFile) {
		return generateImage(imgBase64, new File(imgFile));
	}

}
