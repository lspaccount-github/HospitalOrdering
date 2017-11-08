package com.cn.ssm.utils.util1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * @author Mr.hu
 * @version crateTime：2013-8-5 下午6:47:17
 * Class Explain:
 */
public class RegUtil {

    
    
    private static Logger logger = Logger.getLogger(RegUtil.class);
    
    
    public static boolean isEmail(String email){
        Pattern p = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher m = p.matcher(email);
        logger.info(m.matches()+"---");     
        return m.matches();  
    }  
    
    /**
     * 验证是否是手机号码
     * @param phone
     * @return true 是 false 不是
     */
    public static boolean isMobileNO(String phone){     
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");     
        Matcher m = p.matcher(phone);     
        logger.info(m.matches()+"---");     
        return m.matches();     
    } 
    
    /**
     * 验证是否是用户名
     * 以字母开头，4-20位
     * @param userName
     * @return true 是 false 不是
     */
    public static boolean isUserName(String userName){      
        Pattern p = Pattern.compile("^[a-zA-Z]+([a-zA-Z0-9]|[_]){2,20}$"); 
        Matcher m = p.matcher(userName);
        logger.info(m.matches()+"---");
        return m.matches();
    } 
    
    /**
     * 验证是否是userid
     * @param userId 
     * @return true 是 false 不是
     */
    public static boolean isUserId(String userId){      
        Pattern p = Pattern.compile("[0-9]{1,12}"); 
        Matcher m = p.matcher(userId);      
        logger.info(m.matches()+"---");     
        return m.matches();     
    } 
    
    
    
    /**
     * 验证是否是密码
     * 格式为：6-16位，字母区分大小写
     * @param password
     * @return
     */
    public static boolean isPassWord(String password){       
        //Pattern p = Pattern.compile("^[0-9a-zA-Z\\.\\_]{6,20}$");  
        //Matcher m = p.matcher(password); 
       // logger.info(m.matches()+"---");
        int len = password.length();
        boolean bol = true;
        if(len<6||len>16){
            bol = false;
        }
        return bol; 
    } 
    
    /**
     * 验证是否是手机验证码
     * 6位数字
     * @param password
     * @return
     */
    public static boolean isPhoneProving(String phoneProving){       
        Pattern p = Pattern.compile("^[0-9]{6}$");  
        Matcher m = p.matcher(phoneProving); 
        logger.info(m.matches()+"---");     
        return m.matches();      
    } 
      
    /**
     * 验证字符串是否是正整数
     * @param str
     * @return
     */
    public static boolean isNumber(String str){         
        Pattern p = Pattern.compile("^[0-9]+$");  
        Matcher m = p.matcher(str); 
        logger.info(m.matches()+"---");      
        return m.matches();      
    } 
      
    /**
     * 验证字符串是否是IMEI
     * @param str
     * @return
     */
    public static boolean isIMEI(String str){   
        Pattern p = Pattern.compile("^[0-9]{15}$");  
        Matcher m = p.matcher(str); 
        logger.info(m.matches()+"---");      
        return m.matches();      
    } 
    
    
    public static void main(String[] args) {
              
//       System.out.println(RegUtil.isEmail("189666666666@1-89.co-m.cn"));  
       System.out.println(RegUtil.isMobileNO("18866666666"));   
        // System.out.println(RegUtil.isUserName("a2_2ss号"));    
        // System.out.println(RegUtil.isNumber("0.22222"));   
         //System.out.println(RegUtil.isPhoneProving("222_22"));     
        // System.out.println(RegUtil.isIMEI("11111111111111"));  
    }
    
    
}
