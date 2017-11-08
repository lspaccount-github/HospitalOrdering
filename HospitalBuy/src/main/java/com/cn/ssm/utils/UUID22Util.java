package com.cn.ssm.utils;

import java.util.UUID;

public class UUID22Util {  
    public static final char[] charMap;  
    static {  
        charMap = new char[64];  
        for (int i = 0; i < 10; i++) {  
            charMap[i] = (char) ('0' + i);  
        }  
        for (int i = 10; i < 36; i++) {  
            charMap[i] = (char) ('a' + i - 10);  
        }  
        for (int i = 36; i < 62; i++) {  
            charMap[i] = (char) ('A' + i - 36);  
        }  
        charMap[62] = '_';  
        charMap[63] = '-';  
    }  
  
    public static String hexTo64(String hex) {  
        StringBuffer r = new StringBuffer();  
        int index = 0;  
        int[] buff = new int[3];  
        int l = hex.length();  
        for (int i = 0; i < l; i++) {  
            index = i % 3;  
            buff[index] = Integer.parseInt("" + hex.charAt(i), 16);  
            if (index == 2) {  
                r.append(charMap[buff[0] << 2 | buff[1] >>> 2]);  
                r.append(charMap[(buff[1] & 3) << 4 | buff[2]]);  
            }  
        }  
        return r.toString();  
    }  
  
    public static String getUUID() {  
        StringBuffer sb = new StringBuffer("0");  
        String uuid = UUID.randomUUID().toString();  
        uuid = uuid.replaceAll("-", "").toUpperCase();  
        sb.append(uuid);  
        uuid = hexTo64(sb.toString());  
        return uuid;  
    }  
  
    public static void main(String[] args) {  
  
        int count = 10000000;  
  
        // 计算循环一千万次所用时间  
        long start = System.nanoTime();  
        for (int i = 0; i < count; i++) {  
        }  
        long end = System.nanoTime();  
        long f = end - start;  
        System.out.println("循环10W次所用时间：" + Float.valueOf(f) / 1000000 + "毫秒");  
  
        @SuppressWarnings("unused")  
        String uuid = null;  
        // 计算使用getUUID()得到一千万个数据所用时间  
        start = System.nanoTime();  
        for (int i = 0; i < count; i++)  
            uuid = getUUID();  
        end = System.nanoTime();  
        long l1 = end - start - f;  
        System.out.println("计算使用getUUID()得到10W个数据所用时间:"  
                + (Float.valueOf(end - start - f) / 1000000) + "毫秒");  
  
        // 计算使用randomUUID()得到一千万个数据所用时间  
        start = System.nanoTime();  
        for (int i = 0; i < count; i++)  
            uuid = UUID.randomUUID().toString();  
        end = System.nanoTime();  
        long l2 = end - start - f;  
        System.out.println("计算使用randomUUID()得到10W个数据所用时间:"  
                + (Float.valueOf(end - start - f) / 1000000) + "毫秒");  
  
        System.out.println("用getUUID()取得一个数据所用的时间是用randomUUID()取得一个数据的"  
                + (Float.valueOf(l1) / Float.valueOf(l2)) + "倍");  
    }  
}  