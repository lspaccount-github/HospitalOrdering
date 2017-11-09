package com.cn.ssm.utils;
import java.util.Random;


public class RandomUtil {
	private final static Random rd = new Random();
	private static final String INT = "0123456789";
    private static final String STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String ALL = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	  
    public static enum RndType {
    	INT,
	    STRING,
	    ALL
  	}
    private static String random(int length, RndType rndType) {
    	StringBuilder s = new StringBuilder();
    	char num = 0;
    	for (int i = 0; i < length; i++) {
    		if (rndType.equals(RndType.INT))
    			num =INT.charAt(rd.nextInt(INT.length()));//产生数字0-9的随机数
    		else if (rndType.equals(RndType.STRING))
    			num =STR.charAt(rd.nextInt(STR.length()));//产生字母a-z的随机数
    		else {
    			num = ALL.charAt(rd.nextInt(ALL.length()));
    		}
			s.append(num);
    	}
    	return s.toString();
  	 }
  	public static String randomStr(int length) {
        return random(length, RndType.STRING);
    }
	 
    public static String randomInt(int length) {
        return random(length, RndType.INT);
    }
	 
    public static String randomAll(int length) {
    	return random(length, RndType.ALL);
    }
	  
    public static String randomPassword(boolean isOnlyNum,int length){
    	if(length < 1){
    		return null;
    	}
    	String [] charts=null;
    		if(isOnlyNum){
    			charts=new String[]{"0","1","2","3","4","5","6","7","8","9"};
    		}else{
    			charts=new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    		}
		String temp="";
		for(int i=0;i<length;i++){
			int id = 0;
			if(isOnlyNum){
				id=(int)(Math.random() * 10);
			}else{
				id=(int)(Math.random() * 36);
	  		}
			temp += charts[id];
		}
		return temp;
    }
    public static void main(String[] args) {
    	System.out.println((int)12.9);
    	System.out.println(randomPassword(true, 6));
    	System.out.println(randomAll(6));
    	System.out.println(randomInt(6));
    	System.out.println(randomStr(6));
    }
}
