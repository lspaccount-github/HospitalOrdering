package com.cn.ssm.utils.util1;

import java.io.File;
import java.io.IOException;


/**
 * @author Mr.hu
 * @version crateTime：2013-7-27 上午11:40:29
 * Class Explain:创建文件夹和文件工具类
 */
public class CreateFileUtil {

	/**
	 * 创建文件
	 * 
	 * @param destFileName
	 * @return
	 */
	public static boolean CreateFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists()) {
			//System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
			return false;
		}
		if (destFileName.endsWith(File.separator)) {
			//System.out.println("创建单个文件" + destFileName + "失败，目标不能是目录！");
			return false;
		}
		if (!file.getParentFile().exists()) {
			//System.out.println("目标文件所在路径不存在，准备创建。。。");
			if (!file.getParentFile().mkdirs()) {
				//System.out.println("创建目录文件所在的目录失败！");
				return false;
			}
		} 

		// 创建目标文件
		try {
			if (file.createNewFile()) {
				//System.out.println("创建单个文件" + destFileName + "成功！");
				return true;
			} else {
				//System.out.println("创建单个文件" + destFileName + "失败！");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			//System.out.println("创建单个文件" + destFileName + "失败！");
			return false;
		}
	}

	/**
	 * 创建目录
	 * 
	 * @param destDirName
	 * @return
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			//System.out.println("创建目录" + destDirName + "失败，目标目录已存在！");
			return false;
		}
		if (!destDirName.endsWith(File.separator))
			destDirName = destDirName + File.separator;
		// System.out.println(destDirName); 
		// 创建单个目录
		if (dir.mkdirs()) {
			//System.out.println("创建目录" + destDirName + "成功！");
			return true;
		} else {
			//System.out.println("创建目录" + destDirName + "成功！");
			return false;
		}
	}

	public static String createTempFile(String prefix, String suffix,
			String dirName) {
		File tempFile = null;
		try {
			if (dirName == null) {
				// 在默认文件夹下创建临时文件
				tempFile = File.createTempFile(prefix, suffix);
				return tempFile.getCanonicalPath();
			} else {
				File dir = new File(dirName);
				// 如果临时文件所在目录不存在，首先创建
				if (!dir.exists()) {
					if (!CreateFileUtil.createDir(dirName)) {
						//System.out.println("创建临时文件失败，不能创建临时文件所在目录！");
						return null;
					}
				}
				tempFile = File.createTempFile(prefix, suffix, dir);
				return tempFile.getCanonicalPath();
			}
		} catch (IOException e) {
			e.printStackTrace();
			//System.out.println("创建临时文件失败" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * type 1:图片类型  2：语音类型  3：文字类型
	 * @param file
	 * @return
	 */
	public static Integer getFileType(String file){
		
		Integer fileType=0;
		
		String type=file.substring(file.lastIndexOf(".")+1); 
		if(type!=null && "".equalsIgnoreCase(type)){
			type=type.toLowerCase();
		}
		
		if(type.equalsIgnoreCase("jpg")||type.equalsIgnoreCase("png")||type.equalsIgnoreCase("gif")||type.equalsIgnoreCase("bmp")
				||type.equalsIgnoreCase("jpeg")||type.equalsIgnoreCase("psd")||type.equalsIgnoreCase("emf")||type.equalsIgnoreCase("pic")
				||type.equalsIgnoreCase("jpe")||type.equalsIgnoreCase("dib")||type.equalsIgnoreCase("tiff")||type.equalsIgnoreCase("rle")
				||type.equalsIgnoreCase("bw")||type.equalsIgnoreCase("cdr")||type.equalsIgnoreCase("dwg")||type.equalsIgnoreCase("dxf")
				||type.equalsIgnoreCase("eps")||type.equalsIgnoreCase("ico")||type.equalsIgnoreCase("pcd")||type.equalsIgnoreCase("svg")
				||type.equalsIgnoreCase("wmf")||type.equalsIgnoreCase("cdr")||type.equalsIgnoreCase("col")||type.equalsIgnoreCase("pcx")){
			fileType=1;
		}else if(type.equalsIgnoreCase("amr")||type.equalsIgnoreCase("mp3")||type.equalsIgnoreCase("ape")||type.equalsIgnoreCase("flac")||
				type.equalsIgnoreCase("rm")||type.equalsIgnoreCase("wav")||type.equalsIgnoreCase("mid")){
			fileType=2;
		}else{
			fileType=3;
		}
		//System.out.println(type);  
		return fileType;
	}
	

	public static void main(String[] args) {
		
		
	}
	
}
