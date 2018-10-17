package com.zcj.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author ZhaoChangjin
 * @date ‎2017‎年‎2‎月‎22‎日
 */
public class CaptureUtil {

	private static String strClassName = CaptureUtil.class.getName();
	private static Logger log = Logger.getLogger(strClassName);

	/**
	 * 网络图片保存到本地
	 * 
	 * @param url 图片地址
	 * @param filepath 文件路径
	 * @throws Exception
	 */
	public static void saveAsImg(String url, String filepath) {
		try {
			// new一个URL对象
			URL myUrl = new URL(url);
			// 打开链接
			HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();
			// 设置请求方式为"GET"
			conn.setRequestMethod("GET");
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			byte[] data = readInputStream(inStream);
			// new一个文件对象用来保存图片，默认保存当前工程根目录
			File imageFile = new File(filepath);
			// 创建输出流
			FileOutputStream outStream = new FileOutputStream(imageFile);
			// 写入数据
			outStream.write(data);
			// 关闭输出流
			if (outStream != null) {
				outStream.close();
			}
		} catch (Exception e) {
			log.severe("One File Output Fail");
		}
	}

	/**
	 * 批量保存当前网页所有的图片
	 * 
	 * @param url 图片抓取起始地址
	 * @param dirPath 保存的文件夹路径
	 * @throws Exception
	 */
	public static void saveAllImg(String url, String dirPath) {
		saveAllImg(url, dirPath, null, null, 0);
	}

	/**
	 * 批量保存当前网页指定的图片
	 * 
	 * @param url 图片抓取起始地址
	 * @param dirPath 保存的文件夹路径
	 * @param attribute 标签中图片url的属性名(如："src")
	 * @param extension 图片拓展名(如:".jpg")
	 * @throws Exception
	 */
	public static void saveAllImg(String url, String dirPath, String attribute, String extension) {
		saveAllImg(url, dirPath, attribute, extension, 0);
	}

	/**
	 * 根据条件批量抓取网络图片并保存到本地
	 * 
	 * @param url 图片抓取起始地址
	 * @param dirPath 保存的文件夹路径
	 * @param attribute 标签中图片url的属性名(如："src")
	 * @param extension 图片拓展名(如:".jpg")
	 * @param depth (深度暂不可用)
	 * @throws Exception
	 */
	public static void saveAllImg(String url, String dirPath, String attribute, String extension, int depth) {
		Document doc = null;
		// 根据情况加载(文件或链接)
		try {
			if (!url.startsWith("http")) {
				doc = Jsoup.parse(new File(url), "utf-8");
				// 加载文件
			} else if (isConnect(url)) {
				doc = Jsoup.connect(url).get();// 加载链接
			} else {
				log.severe("链接错误");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (doc != null) {
			File dir = new File(dirPath);
			if (!dir.exists()) {
				dir.mkdirs();// 没有则创建
			}
			Elements imgs = doc.select("img");

			if (attribute == null && extension == null) {
				// 不填标签属性和拓展名(所有图片)
				for (Element element : imgs) {// 遍历所有img标签
					List<String> list = getSrcUrls(element);// 获取元素中所有的资源链接
					for (String srcUrl : list) {
						saveImg(srcUrl, dirPath);
					}
				}
			}

			if (attribute != null && extension == null) {
				// 不填拓展名(所有格式图片)
				for (Element element : imgs) {// 遍历所有img标签
					String srcUrl = element.attr("abs:" + attribute);
					srcUrl = srcUrl.split(" ")[0];
					saveImg(srcUrl, dirPath);
				}
			}

			if (attribute == null && extension != null) {
				// 不填属性名(所有指定拓展名图片)
				for (Element element : imgs) {// 遍历所有img标签
					List<String> list = getSrcUrls(element);// 获取元素中所有的资源链接
					for (String srcUrl : list) {
						if (srcUrl.endsWith(extension)) {
							saveImg(srcUrl, dirPath);
						}
					}
				}
			}
		}
	}

	/**
	 * 保存图片
	 * 
	 * @param srcUrl 图片地址
	 * @param dirPath 文件夹路径
	 * @throws Exception
	 */
	private static void saveImg(String srcUrl, String dirPath) {
		String[] split = srcUrl.split("/");
		String filename = split[split.length - 1];// 获取文件名

		String repalcefilename = filename.replaceAll("[\\/?？:：“\"<>|*]", "");// 过滤非法字符
		if (!isImageFileName(repalcefilename)) {
			repalcefilename = repalcefilename + ".png";// 提取的文件名中不含拓展名,就添加".png"
		}

		if (new File(dirPath + "/" + repalcefilename).exists()) {
			log.warning("文件 \"" + repalcefilename + "\" 已存在");
		} else {
			saveAsImg(srcUrl, dirPath + "/" + repalcefilename);// 保存
			log.info("文件 \"" + repalcefilename + "\" 保存成功");
		}
	}

	/**
	 * 获取元素中所有的资源链接
	 * 
	 * @param element
	 * @return
	 */
	private static List<String> getSrcUrls(Element element) {
		List<String> urllist = new ArrayList<String>();
		Attributes attributes = element.attributes();// 获取元素中所有属性
		List<Attribute> attrs = attributes.asList();// 转换成属性集合
		String attrkey = null;
		String attrValue = null;
		for (Attribute attr : attrs) {// 遍历属性集合
			attrkey = attr.getKey();
			if (attr.getValue().contains("//")) {// 是否为资源链接
				attrValue = element.attr("abs:" + attrkey);
				if (attrValue != null && !attrValue.equals("")) {
					urllist.add((attrValue.split(" "))[0]);// 是资源属性则添加到自定义集合
				}
			}
		}
		return urllist;

	}

	private static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}

	/**
	 * 功能描述 : 检测当前URL是否可连接或是否有效,
	 * 最多连接网络 5 次, 如果 5 次都不成功说明该地址不存在或视为无效地址.
	 * 
	 * @param url 指定URL网络地址
	 * @return String
	 */
	private synchronized static boolean isConnect(String url) {
		URL urlStr;
		HttpURLConnection connection;
		int state = -1;
		Boolean b = false;
		int counts = 0;
		if (url == null || url.length() <= 0) {
			return false;
		}
		while (counts < 5) {
			try {
				urlStr = new URL(url);
				connection = (HttpURLConnection) urlStr.openConnection();
				state = connection.getResponseCode();
				if (state == 200) {
					b = true;
				}
				break;
			} catch (Exception ex) {
				counts++;
				log.info("loop :" + counts);
				continue;
			}
		}
		return b;
	}

	private static boolean isImageFileName(String filename) {
		String[] imgTypes = { "BMP", "GIF", "JPEG", "PNG", "JPG" };
		filename = filename.substring(filename.lastIndexOf(".") + 1);
		for (String imgType : imgTypes) {
			if (filename.equalsIgnoreCase(imgType)) {
				return true;
			}
		}
		return false;
	}

}
