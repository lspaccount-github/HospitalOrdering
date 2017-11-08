package com.cn.ssm.utils.util1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopy {

	/**
	 * 检查文件夹，主程序
	 * 
	 * @param upload_Dir
	 * @param download_Dir
	 */
	public void doMoveFile(String fileNames, String fromFileDir, String saveFileDir) {
		String[] fileNameArray = fileNames.split(",");
		for (int i = 0,len=fileNameArray.length; i <len; i++) {
			if (!checkFileExist(fileNameArray[i], saveFileDir)) {
				copy(fromFileDir + "/" + fileNameArray[i], saveFileDir + "/" + fileNameArray[i]);
				File file = new File(fromFileDir + fileNames);
				if (file.isFile() && file.exists()) {
					file.delete();
				}
			}
		}
	}

	/**
	 * 检查文件是否已经存在 若存在，则返回true 若不存在，则返回false
	 * 
	 * @param fileString
	 * @param dirString
	 * @return
	 */
	public boolean checkFileExist(String fileString, String dirString) {
		String[] dirStrings = new File(dirString).list();
		boolean temp = false;
		for (int i = 0,len=dirStrings.length; i <len ; i++) {
			if (fileString.equals(dirStrings[i])) {
				temp = true;
				break;
			}
		}
		return temp;
	}

	/**
	 * 复制文件，将sourcefile文件复制到targetfile
	 * 
	 * @param sourceFile
	 * @param targetFile
	 */
	public void copy(String sourceFile, String targetFile) {
		FileInputStream input = null;
		FileOutputStream output = null;
		try {
			input = new FileInputStream(sourceFile);
			output = new FileOutputStream(targetFile);
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = input.read(b)) != -1) {
				output.write(b, 0, len);
			}
			output.flush();
			output.close();
			input.close();
			// System.out.println("remove   file:   " + sourceFile + "   ==>   "
			// + targetFile + "   Sucessful!");
		} catch (Exception e) {
			//System.out.println("File   can't   be   read!");
			//System.out.println("该文件正在被使用或创建");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "1.txt";
		FileCopy fc = new FileCopy();
		// 最好在执行之前判断文件夹是否有存在，如果文件夹不存在，复制就没有意义
		// 由于时间关系，这里不再说明了
		fc.doMoveFile(fileName, "d:\\temp", "d:\\test");
	}

}
