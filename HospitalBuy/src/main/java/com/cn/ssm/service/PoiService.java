package com.cn.ssm.service;

import java.io.InputStream;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

public interface PoiService {

	Workbook getWorkbook(InputStream inputStream, String fileName);

	boolean importGoods(Workbook work, Map<String, Object> returnMap);

	boolean importOrders(Workbook work, Map<String, Object> returnMap);
}
