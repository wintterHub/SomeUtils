package com.zcj.test;

import java.util.zip.DataFormatException;

import com.zcj.util.CompressUtil;
import com.zcj.util.FileUtil;

public class CompressTest {

	public static void main(String[] args) throws DataFormatException {
		// String filePath = "D:/Media/ͼƬ/d30f905agy1ffua5n9nzxj21kw0w0e88.jpg";
		// FileOutputStream fos = null;
		// byte[] compress = CompressUtil.compress(FileUtil.getByte(filePath), 9);
		// try {
		// fos = new FileOutputStream("D:/Media/ͼƬ/d30f905agy1ffua5n9nzxj21kw0w0e88");
		// fos.write(compress);
		// } catch (Exception e) {
		// e.printStackTrace();
		// } finally {
		// try {
		// fos.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }

		String filePath = "D:/Media/ͼƬ/d30f905agy1ffua5n9nzxj21kw0w0e88";
		String outFilePath = "D:/Media/ͼƬ/d30f905agy1ffua5n9nzxj21kw0w0e88_.jpg";
		byte[] b = FileUtil.getByte(filePath);
		byte[] uncompress = CompressUtil.uncompress(b);
		FileUtil.byte2File(uncompress, outFilePath);

	}

}
