package com.zcj.test;

import java.util.logging.Logger;

import org.junit.Test;

import com.zcj.util.CaptureUtil;
import com.zcj.util.PasswordUtil;

public class TestJsoup {
	private static String strClassName = TestJsoup.class.getName();
	private static Logger log = Logger.getLogger(strClassName);

	@Test
	public void test() throws Exception {
		log.info("保存中...");
		// IOUtil ioUtil = new IOUtil();
		CaptureUtil.saveAllImg("http://www.icpmp.com/fanhao/daquan.html", "G:/新建文件夹/新建文件夹 (3)", "lz_src", ".jpg", 0);
		log.info("保存任务结束");
	}

	@Test
	public void test2() throws Exception {
		log.info("保存中...");
		// IOUtil.saveAllImg("http://www.mi.com/?from=zhidaoad", "G:/保存图片");// 93
		// IOUtil.saveAllImg("http://www.mi.com/?from=zhidaoad", "G:/保存图片/src", "src", null);//21
		// IOUtil.saveAllImg("http://www.mi.com/?from=zhidaoad", "G:/保存图片/srcset", "srcset", null);// 48
		// IOUtil.saveAllImg("http://www.mi.com/?from=zhidaoad", "G:/保存图片/png", null, ".png");// 52
		CaptureUtil.saveAllImg("https://www.baidu.com/s?f=8&rsv_bp=1&rsv_idx=1&word=%E7%99%BE%E5%BA%A6&tn=98633779_hao_pg", "G:/保存图片/web");//
		log.info("保存任务结束");
	}

	@Test
	public void test3() {
		log.info("保存中...");
		try {
			CaptureUtil.saveAllImg("https://movie.douban.com/", "G:/保存图片/douban/");
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("保存任务结束");
	}

	// b07d92e03a6c96e936d6b222346741264870193f7f34931e
	// 37399934c86c12b51a456d54737b9512fa02c2d221c1998b
	// 893344773321a15053310826092718280720c51977068468
	// a2073358720485c32892bc40c4b92241fe5371fb2d459b4c

	@Test
	public void test4() {
		// String generate = PasswordUtil.generate("123456");
		// System.out.println(generate);
		boolean validate = PasswordUtil.validate("123456", "b07d92e03a6c96e936d6b222346741264870193f7f34931e");
		System.out.println(validate);
	}

}
