package com.zcj.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ZhaoChangjin
 * @date 2017年1月23日
 */
public class MatchUtil {

	private static String strClassName = MatchUtil.class.getName();
	private static Logger log = Logger.getLogger(strClassName);

	/**
	 * 字符串匹配
	 * 
	 * @param str
	 * @param reg
	 * @return
	 */
	public static String getSingle(String str, String reg) {
		String src = null;
		BufferedReader br = null;
		try {
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(str);
			if (m.find()) {
				return src = m.group();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.severe("匹配失败");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return src;
	}

	/**
	 * 获取url资源中的单个匹配值
	 *
	 * @param url 需要被获取资源的链接地址
	 * @param reg 正则表达式
	 * @return
	 */
	public static String getSingle(URL url, String reg) {
		String src = null;
		BufferedReader br = null;
		try {
			URLConnection con = url.openConnection();
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;
			Pattern p = Pattern.compile(reg);
			while ((line = br.readLine()) != null) {// 读取网页每一行
				Matcher m = p.matcher(line);
				if (m.find()) {
					return src = m.group();
				}
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
		return src;
	}

	/**
	 * 获取file资源中的单个匹配值
	 *
	 * @param file
	 * @param reg
	 * @return
	 */
	public static String getSingle(File file, String reg) {
		String src = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			Pattern p = Pattern.compile(reg);
			while ((line = br.readLine()) != null) {// 读取网页每一行
				Matcher m = p.matcher(line);
				if (m.find()) {
					return src = m.group();
				}
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
		return src;
	}

	/**
	 * 批量获取url资源中的匹配值
	 *
	 * @param url
	 * @param reg
	 * @return
	 */
	public static HashMap<String, String> get(URL url, String reg) {
		HashMap<String, String> hm = new HashMap<String, String>();
		BufferedReader br = null;
		int i = 0;
		try {
			URLConnection con = url.openConnection();
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;

			Pattern p = Pattern.compile(reg);
			while ((line = br.readLine()) != null) {// 读取网页每一行
				System.out.println(line);
				Matcher m = p.matcher(line);
				if (m.find()) {
					hm.put(i + "", m.group());// 获取匹配值并储存到HashMap
					i++;
				}
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
		return hm;
	}

	/**
	 * 批量获取file资源中的匹配值
	 *
	 * @param file
	 * @param reg
	 * @return
	 */

	public static HashMap<String, String> get(File file, String reg) {
		HashMap<String, String> hm = new HashMap<String, String>();
		BufferedReader br = null;
		int i = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			Pattern p = Pattern.compile(reg);
			while ((line = br.readLine()) != null) {// 读取每一行
				// System.out.println(line);
				Matcher m = p.matcher(line);
				if (m.find()) {
					hm.put(i + "", m.group());// 获取匹配值并储存到HashMap
					i++;
				}
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
		return hm;
	}

	/**
	 * 批量获取匹配值(URL,File通用)
	 * 
	 * @param url
	 * @param reg
	 * @return
	 */
	public static HashMap<String, String> get(String url, String reg) {
		HashMap<String, String> hm = new HashMap<String, String>();
		BufferedReader br = null;
		int i = 0;
		try {
			URL myUrl = new URL(url);
			URLConnection con = myUrl.openConnection();
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;

			Pattern p = Pattern.compile(reg);
			while ((line = br.readLine()) != null) {// 读取网页每一行
				Matcher m = p.matcher(line);
				if (m.find()) {
					hm.put(i + "", m.group());// 获取匹配值并储存到HashMap
					i++;
				}
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
		return hm;
	}

}
