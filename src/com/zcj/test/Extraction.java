package com.zcj.test;

import java.io.File;
import java.util.logging.Logger;

import org.junit.Test;

import com.zcj.util.CaptureUtil;
import com.zcj.util.FileUtil;

public class Extraction {
	private static String strClassName = Extraction.class.getName();
	private static Logger log = Logger.getLogger(strClassName);

	@Test
	public void test1() {
		for (int id = 36133; id > 0; id--) {
			System.out.println("当前ID:" + id);
			String url = "http://www.szyk15.com/?s=vod-read-id-" + id + ".html";
			String dirPath = "G:\\图片\\抓取\\20171118";
			// IOUtil ioUtil = new IOUtil();
			// https://ebook.chinabuilding.com.cn/zbooklib/sublibBook/resultlist?SiteID=1&sublibID=2118
			log.info("提取中...");
			CaptureUtil.saveAllImg(url, dirPath);
			log.info("批量提取完成");
		}
	}

	@Test
	public void test2() {
		FileUtil.append("C:/Users/acer/Desktop/123.txt", "123");
	}

	@Test
	public void test3() {
		File file = new File("C:/Users/acer/Desktop/FavoritesTypeDataCommand.java");
		System.out.println(file.getPath());
	}
}
