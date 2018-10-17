package com.zcj.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ZhaoChangjin
 * @date 2017年6月1日
 */
public class MacUtil {
	public static String getOsName() {
		String os = "";
		os = System.getProperty("os.name");
		return os;
	}

	/**
	 * 获取本机MAC地址
	 */
	public static String getMACAddress() {
		String address = "";
		String os = getOsName();
		if (os.startsWith("Windows")) {
			try {
				String command = "cmd.exe /c ipconfig /all";
				Process p = Runtime.getRuntime().exec(command);
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "gbk"));
				String line;
				while ((line = br.readLine()) != null) {
					if (line.indexOf("Physical Address") > 0 || line.indexOf("物理地址") > 0) {
						int index = line.indexOf(":");
						index += 2;
						address = line.substring(index);
						break;
					}
				}
				br.close();
				return address.trim();
			} catch (IOException e) {
			}
		} else if (os.startsWith("Linux")) {
			String command = "/bin/sh -c ifconfig -a";
			Process p;
			try {
				p = Runtime.getRuntime().exec(command);
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = br.readLine()) != null) {
					if (line.indexOf("HWaddr") > 0) {
						int index = line.indexOf("HWaddr") + "HWaddr".length();
						address = line.substring(index);
						break;
					}
				}
				br.close();
			} catch (IOException e) {
			}
		}
		address = address.trim();
		return address;
	}
}
