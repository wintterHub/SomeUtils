package com.zcj.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.zcj.util.FileUtil;
import com.zcj.util.MatchUtil;

public class InterfaceDeal {

	public static void main(String[] args) throws IOException {
		HSSFRow row = null;
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		List<File> filelist = FileUtil.getFilelist(new File("E:/zvingworkspace/ZBookLib_ZGJZBZW/JAVA/com/zving/app/impl"));
		for (int i = 0; i < filelist.size(); i++) {
			String single = MatchUtil.getSingle(filelist.get(i), "[ID]{2}\\s\\=\\s\"[A-Za-z]*\"\\;");
			single = single.replace("ID = \"", "");
			single = single.replace("\";", "");
			String single2 = MatchUtil.getSingle(filelist.get(i), "[return]{6}\\s\".*\"\\;");
			single2 = single2.replace("return \"", "");
			single2 = single2.replace("\";", "");
			System.out.println(single2 + "\t" + single);
			row = sheet.createRow(i);
			row.createCell(0).setCellValue(single2 + "接口");
			row.createCell(1).setCellValue(single);
		}
		FileOutputStream fileOut = new FileOutputStream("G:\\" + "接口" + ".xls");
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
	}

}
