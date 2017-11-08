package com.cn.ssm.utils.util1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件
 * 
 * @author Mr.hu
 * 
 */
public class PropertiesUtil {

	/**
	 * 根据propertyFile里面的key得到url值
	 * 
	 * @param key
	 *            url键
	 * @return url地址
	 */
	public static String getUrl(String key, String propertyFile) {
		Properties pro = new Properties();
		InputStream in = PropertiesUtil.class.getResourceAsStream("/" + propertyFile + ".properties");
		String url = "";
		try {
			pro.load(in);
			url = pro.getProperty(key, "#");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return url;
	}

}
