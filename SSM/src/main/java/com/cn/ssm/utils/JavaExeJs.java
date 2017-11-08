package com.cn.ssm.utils;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class JavaExeJs {
	public static void main(String[] args) throws Exception {
		JavaExeJs.javaExejs("D:/program/workspace/SSM/src/main/webapp/common/js/md5.js", "hex_md5", "aa");
	}
	
	public static void javaExejs(String jsFilePath,String funName,Object ... args) throws Exception{
		ScriptEngineManager manager = new ScriptEngineManager();   
		ScriptEngine engine = manager.getEngineByName("javascript");     
		
		FileReader reader = new FileReader(jsFilePath);
		engine.eval(reader);   
		
		if(engine instanceof Invocable) {    
			Invocable invoke = (Invocable)engine;    // 调用merge方法，并传入两个参数    
			//第一个参数为,调用的方法名;插页的参数为多个参数,如:invoke.invokeFunction("方法名","参数1","参数2");
			String c = invoke.invokeFunction(funName,args).toString();    
			System.out.println("加密前:"+args[0].toString());   
			System.out.println("加密后 :"+c);   
		}
		reader.close();
	};
}
