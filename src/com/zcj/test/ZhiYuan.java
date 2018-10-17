package com.zcj.test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.zcj.util.FileUtil;
import com.zcj.util.MatchUtil;

public class ZhiYuan {

	public static void main(String[] args) throws IOException {
		output();
	}

	public static void output() throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow row = null;

		FileSystemView fsv = FileSystemView.getFileSystemView();
		String desktop = fsv.getHomeDirectory().getPath();
		String filePath = desktop + "/ff4.txt";

		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		String line;
		int i = 0;
		int k = 0;
		FileUtil.remove(filePath);
		while ((line = br.readLine()) != null) {
			if (k == 0) {
				row = sheet.createRow(i);
				row.setHeightInPoints(20);
			}
			if (k != 0 && k % 5 == 0) {
				i++;
				row = sheet.createRow(i);
				row.setHeightInPoints(20);
			}
			String str;
			if (macth(line, "^([^\\d]+[\u5927\u5b66\u9662]{2})$")) {
				row.createCell(0).setCellValue(line);
			} else if (macth(line, "^([^\\d]+[\u672c\u79d1\u7b2c\u4e8c\u6279]{2})$")) {
				row.createCell(1).setCellValue("二本");
			} else if (macth(line, "^([^\\d]+[\u672c\u79d1\u7b2c\u4e09\u6279]{2})$")) {
				row.createCell(1).setCellValue("三本");
			} else if (macth(line, "^([^\\d]+[\u672c\u79d1\u6279]{2})$")) {
				row.createCell(1).setCellValue("本科");
			} else if (macth(line, "^(.+[\u7c7b])$")) {
				row.createCell(2).setCellValue(line);
			} else if ((str = MatchUtil.getSingle(line, ":\\s\\d{3}")) != null) {
				row.createCell(3).setCellValue(str.replace(":", "").replace(" ", ""));
			}
			k++;
		}

		sheet.setColumnWidth(0, 30 * 256);

		OutputStream stream = new FileOutputStream(desktop + "/ff4.xls");
		try {
			workbook.write(stream);
			workbook.close();
			stream.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean macth(String str, String reg) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		return m.find();
	}

}
