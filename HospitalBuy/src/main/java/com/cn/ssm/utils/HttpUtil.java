package com.cn.ssm.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

public class HttpUtil {
	
    /**
    * 跨域请求并接收返回的数据
    * 参数说明: url:跨域请求的地址 params:需传递的参数,格式为: 参数1=AAA&参数B=BBB&参数C=CCC
    * @return String
    * @throws IOException
    * @throws HttpException
    */
    public static JSONObject httpClientGet(String url){
    	try{
    		//请求方法
    	    String response=new String();
    	    HttpClient client =new HttpClient();
    	    //解决乱码问题
    	    client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
    	    GetMethod method = new GetMethod(ObjectUtils.toString(url));
    	    client.executeMethod(method);
    	    response = method.getResponseBodyAsString();
    	    if(response.length() > 2 && response.contains("{") && response.contains("}")){
    	    	JSONObject json=JSONObject.fromObject(response);
    	    	return json;
    	    }else{
    	    	return null;
    	    }
    	}catch(Exception e){
    		e.printStackTrace();
    		return new JSONObject();
    	}
	}
    
    /**
    * 跨域请求并接收返回的数据
    * 参数说明: url:跨域请求的地址 
    * params:需传递的参数,格式为: 参数1=AAA&参数B=BBB&参数C=CCC
    * header 设置请求头
    * @return String
    * @throws IOException
    * @throws HttpException
    */
    public static JSONObject httpClientPost(String url, JSONObject params,Map<String,String> header){
    	try{
    		//请求方法
    	    String response=new String();
    	    HttpClient client =new HttpClient();
    	    //解决乱码问题
    	    HttpClientParams clientParams=client.getParams();
    	    clientParams.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
    	    if(header != null && header.size()>0){
    	    	for(Entry<String,String> temp:header.entrySet()){
    	    		clientParams.setParameter(temp.getKey(), temp.getValue());
    	    	}
    	    }
    	    
    	    PostMethod method =new PostMethod(ObjectUtils.toString(url));
    	    if(params != null && !params.isNullObject()){
    	    	for(Object key:params.keySet()){
    	    		if(StringUtils.isNotEmpty(ObjectUtils.toString(params.get(key)))){
    	    			method.addParameter((String)key, ObjectUtils.toString(params.get(key)));
    	    		}else{
    	    			method.addParameter((String)key, "");
    	    		}
    	    	}
    	    }
    	    
    	    client.executeMethod(method);
    	    response = method.getResponseBodyAsString();
    	    JSONObject jsonObject = JSONObject.fromObject(response.toString());
    	    return jsonObject;
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
	}
    
}
