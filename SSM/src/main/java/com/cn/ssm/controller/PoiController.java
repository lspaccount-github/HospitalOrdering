package com.cn.ssm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cn.ssm.service.PoiService;

/**
 * excel导入导出
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/poi")
public class PoiController extends BaseController {

	@Autowired
	private PoiService poiService;
	
	/*
     * 采用file.Transto 来保存上传的文件
     */
    @RequestMapping("/importGoods")
    public void  importGoods(@RequestParam("file") CommonsMultipartFile file,HttpServletResponse response) throws IOException {
    	Map<String,Object> returnMap = new HashMap<String, Object>();
    	InputStream inputStream = null;
    	Workbook work = null;
    	OutputStream outputStream = null;
    	try {
    		String fileName = file.getOriginalFilename();
    		inputStream = file.getInputStream();
    		work = poiService.getWorkbook(inputStream,fileName);
    		if(work == null){
    			error(returnMap, 1, "文件类型错误");
    			return ;
    		}
    		//导入数据
    		boolean flag = poiService.importGoods(work,returnMap);
    		if(!flag){
    			outJson(returnMap);
    			return ;
    		}
    		//outputStream = new FileOutputStream(new File("C:/Users/Administrator/Desktop/importGoods/goods.xlsx"));
    		File fileDir = new File("D:/importGoods/");
    		if (!fileDir.exists()) {
    			fileDir.mkdirs();
    		}
    		outputStream = new FileOutputStream(new File("D:/importGoods/"+new java.text.SimpleDateFormat("yyyyMMddHHmmss")
    		.format(new Date())+"_"+fileName));
    		work.write(outputStream);
    		work.close();
    		outputStream.close();
    		outJson(returnMap);
		} catch (Exception e) {
			e.printStackTrace();
			outJson(returnMap);
		}finally{
			if(inputStream != null)
				inputStream.close();
			if(work != null)
				work.close();
			if(outputStream != null)
				outputStream.close();
		}
    }
    
    /*
     * 采用file.Transto 来保存上传的文件
     */
    @RequestMapping("/importOrder")
    public void  importOrder(@RequestParam("file") CommonsMultipartFile file,HttpServletResponse response) throws IOException {
    	Map<String,Object> returnMap = new HashMap<String, Object>();
    	InputStream inputStream = null;
    	Workbook work = null;
    	OutputStream outputStream = null;
    	try {
    		String fileName = file.getOriginalFilename();
    		inputStream = file.getInputStream();
    		work = poiService.getWorkbook(inputStream,fileName);
    		if(work == null){
    			error(returnMap, 1, "文件类型错误");
    			return ;
    		}
    		//导入数据
    		boolean flag = poiService.importOrders(work, returnMap);
    		if(!flag){
    			outJson(returnMap);
    			return ;
    		}
    		File fileDir = new File("D:/importOrder/");
    		if (!fileDir.exists()) {
    			fileDir.mkdirs();
    		}
    		//outputStream = new FileOutputStream(new File("C:/Users/Administrator/Desktop/importGoods/goods.xlsx"));
			outputStream = new FileOutputStream(new File("D:/importOrder/"+
    				new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+fileName));
			work.write(outputStream);
			work.close();
		 	outputStream.close();
		 	outJson(returnMap);
    	} catch (Exception e) {
    		e.printStackTrace();
    		outJson(returnMap);
    	}finally{
    		if(inputStream != null)
    			inputStream.close();
    		if(work != null)
    			work.close();
    		if(outputStream != null)
    			outputStream.close();
    	}
    }
}
