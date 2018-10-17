package com.zcj.test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.xiph.speex.SpeexDecoder;

public class TestJSpeex {
	public static void main(String[] args) {
		SpeexDecoder speexDecoder = new SpeexDecoder();
		// int processData = 0;
		byte[] byteArr;
		try {
			byteArr = toByteArray(new File("E:\\zvingworkspace\\MHK\\appdata\\speex\\b.speex"));
			// int length = byteArr.length;
			boolean init = speexDecoder.init(1, 16, 1, false);
			System.out.println(init);
			byte[] b = new byte[10];
			for (int i = 0; i < 10; i++) {
				b[i] = byteArr[i];
			}
			speexDecoder.processData(b, 0, 10);
			// System.out.println("processData = " + processData);
			byte[] decoded = new byte[speexDecoder.getProcessedDataByteSize()];
			int processedData = speexDecoder.getProcessedData(decoded, 0);
			System.out.println("processedData = " + processedData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 读取文件的字节数组 */
	public static byte[] toByteArray(File file) throws IOException {
		File f = file;
		if (!f.exists()) {
			throw new FileNotFoundException("file not exists");
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}
}
