package com.cn.ssm.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class UpYunUtil {

	// 运行前先设置好以下三个参数
	public static final String BUCKET_NAME = "kaidianlaa";
	public static final String OPERATOR_NAME = "admin";
	public static final String OPERATOR_PWD = "K911AZ6yR3Clce9HaV58";
	
	/** 绑定的域名 */
	public static final String UpYun_URL = "http://" + BUCKET_NAME+ ".b0.upaiyun.com";

	/** 根目录 */
	public static final String DIR_ROOT = "/";
	
	/** 域名 **/
	public static final String DOMAIN="http://img.kaidianlaa.com/";
	
	private static UpYun upyun = null;
	
	static {
		// 初始化空间
		upyun = new UpYun(UpYunUtil.BUCKET_NAME, UpYunUtil.OPERATOR_NAME, UpYunUtil.OPERATOR_PWD);
		// 切换 API 接口的域名接入点，默认为自动识别接入点
		upyun.setApiDomain(UpYun.ED_AUTO);

		// 设置连接超时时间，默认为30秒
		//upyun.setTimeout(60);

		// 设置是否开启debug模式，默认不开启
		upyun.setDebug(false);
	}
	
	/**
	 * 保存文本文件到又拍云
	 * @param text
	 * @return
	 */
	public static String saveTxt(String txt){
		if(StringUtils.isEmpty(txt)){
			return null;
		}
		String path = getFilePath();
		path = path + UUID.randomUUID() + ".txt";
		try{
			InputStream in=new ByteArrayInputStream(txt.getBytes());
			// 上传文件，并自动创建父级目录（最多10级） filePath 表示上传到又拍云的路径 file
			upyun.writeInputStream(path, in, true,null);
			return path;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取保存txt文件的路径
	 * @return
	 */
	public static String getFilePath(){
		Calendar cal=Calendar.getInstance();
		String filePath="";
		filePath += cal.get(Calendar.YEAR);
		if(cal.get(Calendar.MONTH) < 9){
			filePath += "0"+(cal.get(Calendar.MONTH)+1);
		}else{
			filePath += ""+cal.get(Calendar.MONTH)+1;
		}
		if(cal.get(Calendar.DATE) < 10){
			filePath += "/0"+(cal.get(Calendar.DATE));
		}else{
			filePath += "/"+cal.get(Calendar.DATE);
		}
		return "/upload/file/"+filePath+"/";
	}
	
	/**
	 * 读取text文件的内容
	 * @param path
	 * @return
	 */
	public static String getTxt(String path){
		return upyun.readFile(path);
	}
	
	/**
	 * 更新文件
	 * @param txt 更新内容
	 * @param path 被更新路径
	 */
	public static String updateTxt(String txt,String path){
		if(StringUtils.isEmpty(txt) || StringUtils.isEmpty(path)){
			return null;
		}
		try{
			InputStream in=new ByteArrayInputStream(txt.getBytes());
			// 上传文件，并自动创建父级目录（最多10级） filePath 表示上传到又拍云的路径 file
			upyun.writeInputStream(path, in, true,null);
			return path;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 上传文件到upyun
	 * @param inputStream 上传文件的文件流
	 * @param filePath 上传文件的路径
	 * @return
	 */
	public static String upload(InputStream inputStream,String filePath){
		try{
			// 设置待上传文件的 Content-MD5 值
			// 如果又拍云服务端收到的文件MD5值与用户设置的不一致，将回报 406 NotAcceptable 错误
//			upyun.setContentMD5(UpYun.MD5InputStream(inputStream));
			
			// 上传文件，并自动创建父级目录（最多10级） filePath 表示上传到又拍云的路径 file
			upyun.writeInputStream(filePath, inputStream, true,null);
			return filePath;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加上传文件的任务
	 * @param file 上传的文件
	 * @param filePath 上传的文件路径
	 * @param flag 是否采用异步上传文件
	 * @return
	 */
	public static String addUploadFileTask(final File file, final String filePath,boolean flag){
		if(flag){
			uploadFileTask(file,filePath);
			return "http://img.kaidianlaa.com"+filePath;
		}else{
			return uploadFile(file,filePath);
		}
		
	}
	
	/**
	 * 异步上传文件
	 */
	public static void uploadFileTask(final File file,final String filePath){
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		TaskExecutor taskExecutor=(TaskExecutor) context.getBean("taskExecutor");
		taskExecutor.execute(new Runnable() {
			public void run() {
				uploadFile(file,filePath);
			}
		});
	}
	
	/**
	 * 上传文件到upyun
	 * @param file 上传的文件
	 * @param filePath 上传路径
	 * @return
	 */
	public static String uploadFile(File file,String filePath){
		try {
			// 上传文件，并自动创建父级目录（最多10级） filePath 表示上传到又拍云的路径 file
			upyun.writeFile(filePath, file, true);
			return "http://img.kaidianlaa.com"+filePath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String isSuccess(boolean result) {
		return result ? " 成功" : " 失败";
	}
	
	public static String getURL(String path){
		return UpYunUtil.DOMAIN+path;
	}
	/**
	 * 根据URL 下载文件加前缀
	 * @param urlString
	 */
	public static String downloadPrefix(String urlString, String prefix){
		String filename=prefix+urlString.substring(urlString.lastIndexOf("/")+1);
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = requestAttributes != null ? ((ServletRequestAttributes) requestAttributes).getRequest() : null;
		String savePath=request.getServletContext().getRealPath("/upload/image//");
		//savePath="/user";
		try{
			// 构造URL  
	        URL url = new URL(urlString);  
	        // 打开连接  
	        URLConnection con = url.openConnection();  
	        // 输入流  
	        InputStream is = con.getInputStream();  
	      
	        // 1K的数据缓冲  
	        byte[] bs = new byte[4096];  
	        // 读取到的数据长度  
	        int len;  
	        // 输出的文件流  
	       File sf=new File(savePath);  
	       if(!sf.exists()){  
	           sf.mkdirs();  
	       }  
	       OutputStream os = new FileOutputStream(sf.getPath()+File.separatorChar+filename);  
	        // 开始读取  
	        while ((len = is.read(bs)) != -1) {  
	          os.write(bs, 0, len);  
	        }  
	        // 完毕，关闭所有链接  
	        os.close();  
	        is.close(); 
	        return savePath+File.separatorChar+filename;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 根据URL 下载照片
	 * @param urlString
	 */
	public static String download(String urlString){
		return downloadPrefix(urlString , "");
    }
	
	/**
	 * 从又拍云上删除文件
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath){
		return upyun.deleteFile(filePath);
	}
	
}
