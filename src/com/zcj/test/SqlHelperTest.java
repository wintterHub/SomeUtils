package com.zcj.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.junit.Test;

import com.zcj.dbutil.DBTool;
import com.zcj.util.SqlUtil;

public class SqlHelperTest {

	@Test
	public void test1() {
		SqlUtil sqlUtil = new SqlUtil();
		ArrayList<Object[]> executeQuery = sqlUtil.executeQuery("select * from shm_user");
		for (Object[] obj : executeQuery) {
			System.out.println(obj[0]);
		}
	}

	@Test
	public void test2() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("G:\\123.xls");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		DBTool.table2Excel("shm_user", fos);
	}

}
