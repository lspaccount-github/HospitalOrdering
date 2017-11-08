package com.cn.ssm.utils;  
  
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
  
  
/** 
 * 编码工具类 
 * 1.将byte[]转为各种进制的字符串 
 * 2.base 64 encode 
 * 3.base 64 decode 
 * 4.获取byte[]的md5值 
 * 5.获取字符串md5值 
 * 6.结合base64实现md5加密 
 * @author uikoo9 
 * @version 0.0.6.20140601 
 */  
public class Md5 {  
	
	public static void main(String[] args) throws Exception {  
		String msg = "我爱你";  
		System.out.println("加密前：" + msg);  
		
		String encrypt = md5Encrypt(msg);  
		System.out.println("加密后：" + encrypt);  
	}  
	
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
	  
    protected static MessageDigest messagedigest = null;  
  
    static {  
        try {  
            messagedigest = MessageDigest.getInstance("MD5");  
        } catch (NoSuchAlgorithmException nsaex) {  
            System.err.println(Md5.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");  
            nsaex.printStackTrace();  
        }  
    }  
      
      
    /** 
     * 将byte[]转为各种进制的字符串 
     * @param bytes byte[] 
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制 
     * @return 转换后的字符串 
     */  
    public static String binary(byte[] bytes, int radix){  
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数  
    }  
      
    /** 
     * base 64 encode 
     * @param bytes 待编码的byte[] 
     * @return 编码后的base 64 code 
     */  
    public static String base64Encode(byte[] bytes){  
        return new BASE64Encoder().encode(bytes);  
    }  
      
    /** 
     * base 64 decode 
     * @param base64Code 待解码的base 64 code 
     * @return 解码后的byte[] 
     * @throws Exception 
     */  
    public static byte[] base64Decode(String base64Code) throws Exception{  
        return base64Code.isEmpty() ? null : new BASE64Decoder().decodeBuffer(base64Code);  
    }  
      
    /** 
     * 获取byte[]的md5值 
     * @param bytes byte[] 
     * @return md5 
     * @throws Exception 
     */  
    public static byte[] md5(byte[] bytes) throws Exception {  
        MessageDigest md = MessageDigest.getInstance("MD5");  
        md.update(bytes);  
          
        return md.digest();  
    }  
      
    /** 
     * 获取字符串md5值 
     * @param msg  
     * @return md5 
     * @throws Exception 
     */  
    public static byte[] md5(String msg) throws Exception {  
        return msg.isEmpty() ? null : md5(msg.getBytes());  
    }  
      
    /** 
     * 结合base64实现md5加密 
     * @param msg 待加密字符串 
     * @return 获取md5后转为base64 
     * @throws Exception 
     */  
    public static String md5Encrypt(String msg) throws Exception{  
        return msg.isEmpty() ? null : base64Encode(md5(msg));  
    }  
    
    /** 
     * 功能：加盐版的MD5.返回格式为MD5(密码+{盐值})
     * @param key  要加密的字符串
     * @param salt  盐值
     * @return String 
     */  
    public static String getMD5StringWithSalt(String key, String salt) {  
        if (StringUtils.isEmpty(key)) {  
            throw new IllegalArgumentException("password不能为null");  
        }  
        if (StringUtils.isEmpty(salt)) {
            throw new IllegalArgumentException("salt不能为空");  
        }  
        if ((salt.toString().lastIndexOf("{") != -1) || (salt.toString().lastIndexOf("}") != -1)) {  
            throw new IllegalArgumentException("salt中不能包含 { 或者 }");  
        }  
        return getMD5String(key + "{" + salt.toString() + "}");  
    }  
  
    /** 
     * 功能：得到文件的md5值。 
     * @param file 文件。 
     * @return String 
     * @throws IOException 读取文件IO异常时。 
     */  
    public static String getFileMD5String(File file) throws IOException {  
        FileInputStream in = new FileInputStream(file);  
        FileChannel ch = in.getChannel();  
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,file.length());  
        messagedigest.update(byteBuffer);  
        return bufferToHex(messagedigest.digest());  
    }  
  
    /** 
     * 功能：得到一个字符串的MD5值。 
     * @param str 字符串 
     * @return String 
     */  
    public static String getMD5String(String str) {  
        return getMD5String(str.getBytes());  
    }  
  
    private static String getMD5String(byte[] bytes) {  
        messagedigest.update(bytes);  
        return bufferToHex(messagedigest.digest());  
    }  
  
    private static String bufferToHex(byte bytes[]) {  
        return bufferToHex(bytes, 0, bytes.length);  
    }  
  
    private static String bufferToHex(byte bytes[], int m, int n) {  
        StringBuffer stringbuffer = new StringBuffer(2 * n);  
        int k = m + n;  
        for (int l = m; l < k; l++) {  
            appendHexPair(bytes[l], stringbuffer);  
        }  
        return stringbuffer.toString();  
    }  
  
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {  
        char c0 = hexDigits[(bt & 0xf0) >> 4];  
        char c1 = hexDigits[bt & 0xf];  
        stringbuffer.append(c0);  
        stringbuffer.append(c1);  
    }
      
}  
