package com.zcj.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class FileNameChange {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// File originalDir = new File("D:\\download\\ay\\66\\1-99");
		// File[] listFiles = originalDir.listFiles();
		// mergeFiles("D:\\download\\ay\\ping.ts", listFiles);
		// String originalDirPath = "D:\\download\\ay\\55";
		// String targetDirPath = "D:\\download\\ay\\66";
		// String appendExtension = ".ts";
		// int size = 0;
		// FileUtil.copyAll(originalDirPath, targetDirPath, appendExtension, size);

		String originalDirPath = "D:\\download\\ay\\第六讲";
		String targetDirPath = "D:\\download\\ay\\《科胡特的自体治愈之道》第六讲回放  理心心理";
		// FileUtil.copy1(originalDirPath, targetDirPath);
	}

	public static final int BUFSIZE = 1024 * 8;

	@SuppressWarnings("resource")
	public static void mergeFiles(String outFile, File[] files) {
		FileChannel outChannel = null;
		System.out.println("Merge " + Arrays.toString(files) + " into " + outFile);
		try {
			outChannel = new FileOutputStream(outFile).getChannel();
			for (File f : files) {
				FileChannel fc = new FileInputStream(f).getChannel();
				ByteBuffer bb = ByteBuffer.allocate(BUFSIZE);
				while (fc.read(bb) != -1) {
					bb.flip();
					outChannel.write(bb);
					bb.clear();
				}
				fc.close();
			}
			System.out.println("Merged!! ");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (outChannel != null) {
					outChannel.close();
				}
			} catch (IOException ignore) {
			}
		}
	}

	public static void aaaaa() throws FileNotFoundException, IOException {
		File file = new File("D:\\download\\ay\\ping.ts");
		file.createNewFile();
		RandomAccessFile in = new RandomAccessFile(file, "rw");
		in.setLength(0);
		in.seek(0);
		byte[] bytes = new byte[10240];
		int len = -1;
		int idnex = 0;
		File originalDir = new File("D:\\download\\ay\\66");
		File[] listFiles = originalDir.listFiles();
		for (File fileOnWin : listFiles) {
			System.out.println("大圈" + idnex++);
			RandomAccessFile out = new RandomAccessFile(fileOnWin, "rw");
			while ((len = out.read(bytes)) != -1) {
				System.out.println("小圈" + idnex++);
				in.write(bytes, 0, len);
			}
			out.close();
		}
		in.close();
	}

}
