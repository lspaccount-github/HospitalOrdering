package com.cn.ssm.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
  
public class HttpClient {  
	private HttpURLConnection conn = null;
	public static final String REQUEST_URL = "http://192.168.0.247:12345/";
	
	public HttpClient(String url) throws IOException{
		//创建URL，协议、ip、端口、访问的页面  
		URL url1 = new URL(url);
		//创建HttpURLConnection 对象  
		this.conn=(HttpURLConnection)url1.openConnection();  
		//设置请求方式POST  
		this.conn.setRequestMethod("POST");  
		this.conn.setRequestProperty("Content-Type", "application/xml");  
		this.conn.setRequestProperty("Connection", "Close"); //长连接、短连接
		this.conn.setDoOutput(true);
		this.conn.setDoInput(true);
	}
	
    public void  transfer(String outputAccount,String inputAccount,Double amount) throws IOException{
    	 String request_sn = randomPassword(true, 9);
    	 String requestXml = "requestXml=<?xml version='1.0' encoding='GB2312' standalone='yes' ?>"  
    		        + "<TX>"  
    		          + "<REQUEST_SN>"+request_sn+"</REQUEST_SN>"  
    		          + "<CUST_ID>JXP314676206#001</CUST_ID>"  
    		          + "<USER_ID>WLPT03</USER_ID>"  
    		          + "<PASSWORD>111111</PASSWORD>"  
    		          + "<TX_CODE>6W8010</TX_CODE>"  
    		          + "<LANGUAGE>CN</LANGUAGE>"  
    		          + "<TX_INFO>"  
    		            + "<PAY_ACCNO>"+outputAccount+"</PAY_ACCNO>"  
    		            + "<RECV_ACCNO>"+inputAccount+"</RECV_ACCNO>"  
    		            + "<RECV_ACC_NAME>廖七五</RECV_ACC_NAME>"  
    		            + "<CHK_RECVNAME>0</CHK_RECVNAME>"  
    		            + "<RECV_OPENACC_DEPT>江西建行</RECV_OPENACC_DEPT>"  
    		            + "<AMOUNT>"+amount.toString()+"</AMOUNT>"  
    		            + "<CUR_TYPE>01</CUR_TYPE>"  
    		            + "<CST_PAY_NO>222222</CST_PAY_NO>"  
    		            + "<USEOF>用途</USEOF>  "
    		            + "<REM1>备注1</REM1>"  
    		            + "<REM2>备注2</REM2>"  
    		          + "</TX_INFO>  "
    		          + "<SIGN_INFO>签名信息</SIGN_INFO>"  
    		          + "<SIGNCERT>sadfaf</SIGNCERT>"  
    		          + "</TX>" ;
    	 System.out.println("==========行内单笔转账(请求报文)==========");
         System.out.println(requestXml);
     	 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "GB2312"));
         writer.write(requestXml);
         writer.flush();
         BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GB2312"));
         //得到HTTP响应码  
         int code=conn.getResponseCode();  
         System.out.println(code);
         System.out.println("==========行内单笔转账(回调报文)==========");
         //获取返回报文  
         String line="";  
         while(!((line = reader.readLine())==null))  
         	System.out.println(line);
    }
    public void queryBalance(String account) throws UnsupportedEncodingException, IOException{
    	String request_sn = randomPassword(true, 9);
    	String requestXml = "requestXml=<?xml version='1.0' encoding='GB2312' standalone='yes' ?>"  
    						+ "<TX>"
						    	  + "<REQUEST_SN>"+request_sn+"</REQUEST_SN>"  
						    	  + "<CUST_ID>JXP314676206#001</CUST_ID>"  
						    	  + "<USER_ID>WLPT03</USER_ID>"
						    	  + "<PASSWORD>111111</PASSWORD>"  
						    	  + "<TX_CODE>6W0100</TX_CODE>"  
						    	  + "<LANGUAGE>CN</LANGUAGE>"  
						    	  + "<TX_INFO>"  
						    	    + "<ACC_NO>"+account+"</ACC_NO>"  
						    	  + "</TX_INFO>"  
				    	  	+ "</TX>";
    	 System.out.println("==========查询账户余额(请求报文)==========");
         System.out.println(requestXml);
     	 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "GB2312"));
         writer.write(requestXml);
         writer.flush();
         BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GB2312"));
         //得到HTTP响应码  
         int code=conn.getResponseCode();  
         System.out.println(code);
         System.out.println("==========查询账户余额(回调报文)==========");
         //获取返回报文  
         String line="";  
         while(!((line = reader.readLine())==null))  
         	System.out.println(line);
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
    
    public void queryBank() throws UnsupportedEncodingException, IOException{
    	String request_sn = randomPassword(true, 9);
    	String requestXml = "requestXml=<?xml version='1.0' encoding='GB2312' standalone='yes' ?>"  
    						+ "<TX>"
						    	  + "<REQUEST_SN>"+request_sn+"</REQUEST_SN>"  
						    	  + "<CUST_ID>JXP314676206#001</CUST_ID>"  
						    	  + "<USER_ID>WLPT03</USER_ID>"
						    	  + "<PASSWORD>111111</PASSWORD>"  
						    	  + "<TX_CODE>6W0201</TX_CODE>"  
						    	  + "<LANGUAGE>CN</LANGUAGE>"  
						    	  + "<TX_INFO>"  
						    	  + "</TX_INFO>"  
				    	  	+ "</TX>";
    	 System.out.println("==========查询银行(请求报文)==========");
         System.out.println(requestXml);
     	 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "GB2312"));
         writer.write(requestXml);
         writer.flush();
         BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GB2312"));
         //得到HTTP响应码  
         int code=conn.getResponseCode();  
         System.out.println(code);
         System.out.println("==========查询银行(回调报文)==========");
         //获取返回报文  
         String line="";  
         while(!((line = reader.readLine())==null))  
         	System.out.println(line);
    }
    public static void main(String[] args)throws Exception {  
    	/*HttpClient httpClient = new HttpClient(REQUEST_URL);
    	//转账
    	httpClient.transfer("44250100003400000866", "44250100003400000865", 100.00);
    	//查询余额
    	HttpClient httpClient2 = new HttpClient(REQUEST_URL);
    	httpClient2.queryBalance("36001250310052510755");*/
    	/*HttpClient httpClient3 = new HttpClient(REQUEST_URL);
    	httpClient3.queryBank();*/
    	System.out.println(11738/1000*1000);
    }  
}  
