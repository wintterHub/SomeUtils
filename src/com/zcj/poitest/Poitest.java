package com.zcj.poitest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.zcj.util.SqlUtil;

public class Poitest {

	private static final String TABLE_NAME = "shm_manager";

	@Test
	/*
	 * 导出数据库数据到Excel
	 */
	public void testPoi() throws IOException {
		SqlUtil sqlUtil = new SqlUtil();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		ArrayList<Object[]> tableProp = sqlUtil.executeQuery("select column_name from information_schema.columns where table_name = ?",
				TABLE_NAME);
		ArrayList<Object[]> listResult = sqlUtil.executeQuery("select * from " + TABLE_NAME);
		HSSFRow row = null;

		row = sheet.createRow(0);

		for (int k = 0; k < tableProp.size() - 1; k++) {
			row.createCell(k).setCellValue(tableProp.get(k)[0].toString());
		}

		for (int i = 0; i < listResult.size() - 1; i++) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < listResult.get(i).length - 1; j++) {
				if (listResult.get(i)[j] == null) {
					row.createCell(j).setCellValue("NULL");
				} else {
					row.createCell(j).setCellValue(listResult.get(i)[j].toString());
				}
			}
		}

		FileOutputStream fileOut = new FileOutputStream("G:\\" + TABLE_NAME + ".xls");
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
	}

}
