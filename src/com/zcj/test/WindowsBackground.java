package com.zcj.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.zcj.util.FileUtil;

public class WindowsBackground {

	private static String strClassName = WindowsBackground.class.getName();
	private static Logger log = Logger.getLogger(strClassName);

	public static void main(String[] args) {
		String filepath1 = "C:/Users/15053/AppData/Local/Packages/Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy/LocalState/Assets";
		String filepath2 = "G:/图片/壁纸/win10/" + new SimpleDateFormat("yyyyMMdd").format(new Date());
		FileUtil.copyAll(filepath1, filepath2, ".jpg", 100000);
		log.info("批量复制文件完成");
	}

}