package com.zcj.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * 文件：复制、剪切(X)、删除、重命名(X)、压缩、解压(X)、转输入流、转字符数组、切割(X)、合并(X)
 * </p>
 * <p>
 * 文件夹：复制、剪切(X)、删除、清空、重命名(X)、遍历
 * </p>
 * <p>
 * 其他：写文件、读文件、输入流转文件、字符数组转文件、网页转文件
 * </p>
 * 
 * @author ZhaoChangjin
 * @date 2017年7月17日
 */
public class FileUtil {

	private static String strClassName = FileUtil.class.getName();
	private static Logger log = Logger.getLogger(strClassName);

	private static List<File> filelist = new ArrayList<File>();

	/**
	 * 递归遍历文件夹中所有文件
	 * 
	 * @param dirpath 需要遍历的文件夹
	 * @return List<File>
	 */
	public static List<File> getFilelist(String dirpath) {
		File file = new File(dirpath);
		return getFilelist(file);
	}

	/**
	 * 递归遍历文件夹中所有文件
	 * 
	 * @param directory 需要遍历的文件夹
	 * @return List<File>
	 */
	public static List<File> getFilelist(File file) {
		if (file.isFile()) {
			filelist.add(file);
		} else if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File listFile : listFiles) {
				getFilelist(listFile);
			}
		}
		return filelist;
	}

	/**
	 * 复制文件
	 * 
	 * @param originalFile 原文件
	 * @param newFile 新文件
	 */
	public static void copy(String originalpath, String newpath) {
		File originalFile = new File(originalpath);
		File newFile = new File(newpath);
		copy(originalFile, newFile);
	}

	/**
	 * 复制文件
	 * 
	 * @param originalFile 原文件
	 * @param targetFile 新文件
	 */
	public static void copy(File originalFile, File targetFile) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new BufferedInputStream(new FileInputStream(originalFile));
			os = new BufferedOutputStream(new FileOutputStream(targetFile));
			byte[] b = new byte[4096];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				os.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.severe("Copy File Fail");
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量复制文件
	 * 
	 * @param originalDirPath 原始文件夹路径
	 * @param targetDirPath 目标文件夹路径
	 */
	public static void copyAll(String originalDirPath, String targetDirPath) {
		File originalDir = new File(originalDirPath);
		File targetDir = new File(targetDirPath);
		copyAll(originalDir, targetDir, "", 0L);
	}

	/**
	 * 批量复制文件
	 * 
	 * @param originalDir 原始文件夹路径
	 * @param targetDir 目标文件夹路径
	 */
	public static void copyAll(File originalDir, File targetDir) {
		copyAll(originalDir, targetDir, "", 0L);
	}

	/**
	 * 批量复制文件
	 * 
	 * @param originalDirPath 原始文件夹路径
	 * @param targetDirPath 目标文件夹路径
	 * @param extension 文件名批量追加字符串
	 * @param size 文件大小过滤（字节），将小于该值的文件排除
	 */
	public static void copyAll(String originalDirPath, String targetDirPath, String appendExtension, long size) {
		File originalDir = new File(originalDirPath);
		File targetDir = new File(targetDirPath);
		copyAll(originalDir, targetDir, appendExtension, size);
	}

	/**
	 * 批量复制文件
	 * 
	 * @param originalDir 原始文件夹路径
	 * @param targetDir 目标文件夹路径
	 * @param extension 文件名批量追加字符串
	 * @param size 文件大小过滤（字节），将小于该值的文件排除
	 */
	public static void copyAll(File originalDir, File targetDir, String appendExtension, long size) {
		if (!originalDir.exists()) {
			log.severe("Original Directory Is Not Exists");
		} else {
			if (!targetDir.exists()) {
				targetDir.mkdir();
			}
			for (File fileOnWin : originalDir.listFiles()) {
				File newFile = new File(targetDir.getPath() + "/" + fileOnWin.getName() + appendExtension);
				if (fileOnWin.length() > size) {
					if (!newFile.exists()) {
						filelist.add(fileOnWin);
						copy(fileOnWin, newFile);
					} else {
						log.info("File " + newFile.getName() + "Is Exists");
					}
				}
			}
		}
	}

	/**
	 * 文件切割 -待完成
	 * 
	 * @param originalFilePath
	 * @param targetDirPath
	 */
	public static void split(String originalFilePath, String targetDirPath) {
		split(new File(originalFilePath), new File(targetDirPath));
	}

	/**
	 * 文件切割 -待完成
	 * 
	 * @param originalFile
	 * @param targetDir
	 */
	public static void split(File originalFile, File targetDir) {

	}

	/**
	 * 文件合并 -待完成
	 * 
	 * @param originalDir
	 * @param targetDir
	 */
	public static void consolidation(File originalDir, File targetDir) {
		for (File fileOnWin : originalDir.listFiles()) {
			Integer x = Integer.parseInt(fileOnWin.getName().split("_")[1], 16);
			File newFile = new File(targetDir.getPath() + "/" + x);
			if (!newFile.exists()) {
				filelist.add(fileOnWin);
				copy(fileOnWin, newFile);
			} else {
				log.info("File " + newFile.getName() + "Is Exists");
			}
		}
	}

	/**
	 * 文件合并 -待完成
	 * 
	 * @param originalDirPath
	 * @param targetDirPath
	 */
	public static void consolidation(String originalDirPath, String targetDirPath) {
		consolidation(new File(originalDirPath), new File(targetDirPath));
	}

	/**
	 * 批量提取
	 * 
	 * @param originalDirPath 原始文件夹路径
	 * @param targetDirPath 目标文件夹路径
	 * @param extension 被提取文件的后缀筛选
	 * @param size 文件大小过滤（字节），将小于该值的文件排除
	 */
	public static void extractionAll(String originalDirPath, String targetDirPath, String extension, long size) {
		File originalDir = new File(originalDirPath);
		File newDir = new File(targetDirPath);
		if (!originalDir.exists()) {
			log.severe("Original Directory Is Not Exists");
		} else if (!newDir.exists()) {
			log.severe("Target Directory Is Not Exists");
		} else {
			List<File> allfilelist = getFilelist(originalDir);
			for (File file : allfilelist) {
				if (file.getName().endsWith(extension) && file.length() > size) {
					copy(file, new File(targetDirPath + "/" + file.getName()));
				}
			}
		}
	}

	/**
	 * 将字符串写入文件
	 * 
	 * @param filepath
	 * @param content
	 */
	public static void wirte(String filepath, String content) {
		wirte(new File(filepath), content);
	}

	/**
	 * 将字符串写入文件
	 * 
	 * @param path
	 * @param content
	 */
	public static void wirte(File file, String content) {
		FileWriter fos = null;
		try {
			fos = new FileWriter(file);
			fos.write(content);
		} catch (IOException ex) {
			Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException ex) {
				Logger.getLogger(FileUtil.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * 将字符串追加到文件末尾
	 * 
	 * @param filepath
	 * @param content
	 */
	public static void append(String filepath, String content) {
		append(new File(filepath), content);
	}

	/**
	 * 将字符串追加到文件末尾
	 * 
	 * @param path
	 * @param content
	 */
	public static void append(File file, String content) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String oStr = read(file);
		StringBuffer sb = new StringBuffer();
		sb.append(oStr);
		sb.append(content);
		wirte(file, sb.toString());
	}

	/**
	 * 读取文件
	 * 
	 * @param filepath
	 * @return
	 */
	public static String read(String filepath) {
		return read(new File(filepath));
	}

	/**
	 * 读取文件
	 * 
	 * @param filepath
	 * @return
	 */
	public static String read(File file) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 保存网页内容为文本
	 * 
	 * @param filepath
	 * @param url
	 */
	public static void wirteUrl(String filepath, String url) {
		wirte(filepath, readUrl(url));
	}

	/**
	 * 保存网页内容为文本
	 * 
	 * @param file
	 * @param url
	 */
	public static void wirteUrl(File file, String url) {
		wirte(file, readUrl(url));
	}

	/**
	 * 获取网页文本
	 * 
	 * @param url 网址
	 */
	public static String readUrl(String url) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			URL myUrl = new URL(url);
			URLConnection con = myUrl.openConnection();
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 清空文件夹
	 * 
	 * @param dirpath
	 */
	public static boolean clearDir(String dirpath) {
		return clearDir(new File(dirpath));
	}

	/**
	 * 清空文件夹
	 * 
	 * @param file
	 */
	public static boolean clearDir(File file) {
		for (File f : file.listFiles()) {
			remove(f);
		}
		return true;
	}

	/**
	 * 删除文件夹
	 * 
	 * @param file
	 */
	private static boolean removeDir(File file) {
		if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File f : listFiles) {
				if (f.isDirectory()) {
					if (f.listFiles().length == 0) {
						f.delete();
					} else {
						removeDir(f);
					}
				} else {
					f.delete();
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 */
	private static boolean removeFile(File file) {
		if (file.isFile()) {
			return file.delete();
		} else {
			return false;
		}
	}

	/**
	 * 删除文件或文件夹
	 * 
	 * @param path
	 */
	public static boolean remove(String dirpath) {
		return remove(new File(dirpath));
	}

	/**
	 * 删除文件或文件夹
	 * 
	 * @param file
	 */
	public static boolean remove(File file) {
		if (file.isDirectory()) {
			return removeDir(file);
		} else {
			return removeFile(file);
		}
	}

	// 复制、剪切、重命名文件夹，剪切、重命名文件，解压缩，切割

	/**
	 * 文件转byte数组
	 * 
	 * @param filepath
	 * @return
	 */
	public static byte[] getByte(String filepath) {
		return getByte(new File(filepath));
	}

	/**
	 * 文件转byte数组
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] getByte(File file) {
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		byte[] ret = null;
		try {
			fis = new FileInputStream(file);
			baos = new ByteArrayOutputStream(1024 * 64);
			byte[] b = new byte[1024 * 64];
			int len = 0;
			while ((len = fis.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			ret = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				baos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * 字节数组转文件
	 * 
	 * @param b 字节数组
	 * @param filepath 保存文件路径
	 */
	public static void byte2File(byte[] b, String filepath) {
		byte2File(b, new File(filepath));
	}

	/**
	 * 字节数组转文件
	 * 
	 * @param b 字节数组
	 * @param file 保存文件
	 */
	public static void byte2File(byte[] b, File file) {
		int len = b.length;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(b, 0, len);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件转输入流
	 * 
	 * @param filepath
	 * @return
	 */
	public static InputStream getStream(String filepath) {
		return getStream(new File(filepath));
	}

	/**
	 * 文件转输入流
	 * 
	 * @param file
	 * @return
	 */
	public static InputStream getStream(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fis;
	}

	/**
	 * 输入流转文件
	 * 
	 * @param inputstream 输入流
	 * @param filepath 保存文件路径
	 */
	public static void stream2File(InputStream inputstream, String filepath) {
		stream2File(inputstream, new File(filepath));
	}

	/**
	 * 输入流转文件
	 * 
	 * @param inputstream 输入流
	 * @param file 保存文件
	 */
	public static void stream2File(InputStream inputstream, File file) {
		byte[] b = new byte[1024 * 64];
		int len = 0;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			while ((len = inputstream.read(b)) != -1) {
				fos.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件压缩
	 * 
	 * @param inputfile
	 * @param outputfile
	 */
	public static void compress(String inputFilePath, String outputFilePath) {
		compress(new File(inputFilePath), new File(outputFilePath));
	}

	/**
	 * 文件压缩
	 * 
	 * @param inputfile
	 * @param outputfile
	 */
	public static void compress(File inputfile, File outputfile) {
		FileOutputStream fos = null;
		byte[] compress = CompressUtil.compress(getByte(inputfile), 9);
		try {
			fos = new FileOutputStream(outputfile);
			fos.write(compress);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
