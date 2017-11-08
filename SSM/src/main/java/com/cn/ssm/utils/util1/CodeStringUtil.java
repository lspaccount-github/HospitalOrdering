package com.cn.ssm.utils.util1;

public class CodeStringUtil {
	
	/**
	 * 获取激活码
	 * 
	 * @param count
	 * @return
	 */
	public static String getActivationCode(int count) {
		
		return UUIDUtils.getUUID(count);
	}
	
	
}
