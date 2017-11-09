package com.cn.ssm.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
  
public class HttpClient2 {  
	private HttpURLConnection conn = null;
	public static final String REQUEST_URL = "https://ibsbjstar.ccb.com.cn/app/ccbMain";
	public static final String CHARSET = "GB2312";
	public HttpClient2(String url) throws IOException{
		//创建URL，协议、ip、端口、访问的页面  
		URL url1 = new URL(url);
		//创建HttpURLConnection 对象  
		this.conn=(HttpURLConnection)url1.openConnection();  
		//设置请求方式POST  
		this.conn.setRequestMethod("POST");  
		this.conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		this.conn.setRequestProperty("Accept-Charset", CHARSET);
		// 发送POST请求必须设置如下两行
		conn.setDoOutput(true);
		conn.setDoInput(true);
	}
	
    public void  transfer() throws IOException{
     	 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "GB2312"));
         writer.write("MERCHANTID=105421000002199&BRANCHID=360000000&POSID=001176031&ORDERDATE=&BEGORDERTIME=&ENDORDERTIME=&BEGORDERID=1vi8d3jYl76oCNuwDPgG4g&ENDORDERID=1vi8d3jYl76oCNuwDPgG4g&QUPWD=111111&TXCODE=410408&SEL_TYPE=2&OPERATOR=&MAC=c6b1ee726d413246917f5ea6e721abc9");
         //writer.write("MERCHANTID=105421000002199&POSID=001176031&BRANCHID=360000000&ORDERID=43588&PAYMENT=8.00&CURCODE=01&TXCODE=690401&REMARK1=s&REMARK2=s&MAC=64ba76562cd974d80f1caa01c35db440");
         writer.flush();
         BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), CHARSET));
         //得到HTTP响应码  
         int code=conn.getResponseCode();  
         System.out.println(code);
         System.out.println("==========行内单笔转账(回调报文)==========");
         //获取返回报文  
         String line="";  
         while(!((line = reader.readLine())==null))  
         	System.out.println(line);
    }
    public static String ccbMd5(String data) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		try {
			engine.eval(""
					+ "var hexcase = 0;"
					+ "var b64pad  = \"\";"
					+ "var chrsz   = 8;"
					+ "function hex_md5(s){ return binl2hex(core_md5(str2binl(s), s.length * chrsz));}"
					+ "function b64_md5(s){ return binl2b64(core_md5(str2binl(s), s.length * chrsz));}"
					+ "function str_md5(s){ return binl2str(core_md5(str2binl(s), s.length * chrsz));}"
					+ "function hex_hmac_md5(key, data) { return binl2hex(core_hmac_md5(key, data)); }"
					+ "function b64_hmac_md5(key, data) { return binl2b64(core_hmac_md5(key, data)); }"
					+ "function str_hmac_md5(key, data) { return binl2str(core_hmac_md5(key, data)); }"
					+ ""
					+ "function md5_vm_test()"
					+ "{"
					+ "	 return hex_md5(\"abc\") == \"900150983cd24fb0d6963f7d28e17f72\";"
					+ "}"
					+ ""
					+ "function core_md5(x, len)"
					+ "{"
					+ "x[len >> 5] |= 0x80 << ((len) % 32);"
					+ "x[(((len + 64) >>> 9) << 4) + 14] = len;"
					+ ""
					+ "var a =  1732584193;"
					+ "var b = -271733879;"
					+ "var c = -1732584194;"
					+ "var d =  271733878;"
					+ ""
					+ "for(var i = 0; i < x.length; i += 16)"
					+ "{"
					+ "var olda = a;"
					+ "var oldb = b;"
					+ "var oldc = c;"
					+ "var oldd = d;"
					+ ""
					+ "a = md5_ff(a, b, c, d, x[i+ 0], 7 , -680876936);"
					+ "d = md5_ff(d, a, b, c, x[i+ 1], 12, -389564586);"
					+ "c = md5_ff(c, d, a, b, x[i+ 2], 17,  606105819);"
					+ "b = md5_ff(b, c, d, a, x[i+ 3], 22, -1044525330);"
					+ "a = md5_ff(a, b, c, d, x[i+ 4], 7 , -176418897);"
					+ "d = md5_ff(d, a, b, c, x[i+ 5], 12,  1200080426);"
					+ "c = md5_ff(c, d, a, b, x[i+ 6], 17, -1473231341);"
					+ "b = md5_ff(b, c, d, a, x[i+ 7], 22, -45705983);"
					+ "a = md5_ff(a, b, c, d, x[i+ 8], 7 ,  1770035416);"
					+ "d = md5_ff(d, a, b, c, x[i+ 9], 12, -1958414417);"
					+ "c = md5_ff(c, d, a, b, x[i+10], 17, -42063);"
					+ "b = md5_ff(b, c, d, a, x[i+11], 22, -1990404162);"
					+ "a = md5_ff(a, b, c, d, x[i+12], 7 ,  1804603682);"
					+ "d = md5_ff(d, a, b, c, x[i+13], 12, -40341101);"
					+ "c = md5_ff(c, d, a, b, x[i+14], 17, -1502002290);"
					+ "b = md5_ff(b, c, d, a, x[i+15], 22,  1236535329);"
					+ ""
					+ "a = md5_gg(a, b, c, d, x[i+ 1], 5 , -165796510);"
					+ "d = md5_gg(d, a, b, c, x[i+ 6], 9 , -1069501632);"
					+ "c = md5_gg(c, d, a, b, x[i+11], 14,  643717713);"
					+ "b = md5_gg(b, c, d, a, x[i+ 0], 20, -373897302);"
					+ "a = md5_gg(a, b, c, d, x[i+ 5], 5 , -701558691);"
					+ "d = md5_gg(d, a, b, c, x[i+10], 9 ,  38016083);"
					+ "c = md5_gg(c, d, a, b, x[i+15], 14, -660478335);"
					+ "b = md5_gg(b, c, d, a, x[i+ 4], 20, -405537848);"
					+ "a = md5_gg(a, b, c, d, x[i+ 9], 5 ,  568446438);"
					+ "d = md5_gg(d, a, b, c, x[i+14], 9 , -1019803690);"
					+ "c = md5_gg(c, d, a, b, x[i+ 3], 14, -187363961);"
					+ "b = md5_gg(b, c, d, a, x[i+ 8], 20,  1163531501);"
					+ "a = md5_gg(a, b, c, d, x[i+13], 5 , -1444681467);"
					+ "d = md5_gg(d, a, b, c, x[i+ 2], 9 , -51403784);"
					+ "c = md5_gg(c, d, a, b, x[i+ 7], 14,  1735328473);"
					+ "b = md5_gg(b, c, d, a, x[i+12], 20, -1926607734);"
					+ ""
					+ "a = md5_hh(a, b, c, d, x[i+ 5], 4 , -378558);"
					+ "d = md5_hh(d, a, b, c, x[i+ 8], 11, -2022574463);"
					+ "c = md5_hh(c, d, a, b, x[i+11], 16,  1839030562);"
					+ "b = md5_hh(b, c, d, a, x[i+14], 23, -35309556);"
					+ "a = md5_hh(a, b, c, d, x[i+ 1], 4 , -1530992060);"
					+ "d = md5_hh(d, a, b, c, x[i+ 4], 11,  1272893353);"
					+ "c = md5_hh(c, d, a, b, x[i+ 7], 16, -155497632);"
					+ "b = md5_hh(b, c, d, a, x[i+10], 23, -1094730640);"
					+ "a = md5_hh(a, b, c, d, x[i+13], 4 ,  681279174);"
					+ "d = md5_hh(d, a, b, c, x[i+ 0], 11, -358537222);"
					+ "c = md5_hh(c, d, a, b, x[i+ 3], 16, -722521979);"
					+ "b = md5_hh(b, c, d, a, x[i+ 6], 23,  76029189);"
					+ "a = md5_hh(a, b, c, d, x[i+ 9], 4 , -640364487);"
					+ "d = md5_hh(d, a, b, c, x[i+12], 11, -421815835);"
					+ "c = md5_hh(c, d, a, b, x[i+15], 16,  530742520);"
					+ "b = md5_hh(b, c, d, a, x[i+ 2], 23, -995338651);"
					+ ""
					+ "a = md5_ii(a, b, c, d, x[i+ 0], 6 , -198630844);"
					+ "d = md5_ii(d, a, b, c, x[i+ 7], 10,  1126891415);"
					+ "c = md5_ii(c, d, a, b, x[i+14], 15, -1416354905);"
					+ "b = md5_ii(b, c, d, a, x[i+ 5], 21, -57434055);"
					+ "a = md5_ii(a, b, c, d, x[i+12], 6 ,  1700485571);"
					+ "d = md5_ii(d, a, b, c, x[i+ 3], 10, -1894986606);"
					+ "c = md5_ii(c, d, a, b, x[i+10], 15, -1051523);"
					+ "b = md5_ii(b, c, d, a, x[i+ 1], 21, -2054922799);"
					+ "a = md5_ii(a, b, c, d, x[i+ 8], 6 ,  1873313359);"
					+ "d = md5_ii(d, a, b, c, x[i+15], 10, -30611744);"
					+ "c = md5_ii(c, d, a, b, x[i+ 6], 15, -1560198380);"
					+ "b = md5_ii(b, c, d, a, x[i+13], 21,  1309151649);"
					+ "a = md5_ii(a, b, c, d, x[i+ 4], 6 , -145523070);"
					+ "d = md5_ii(d, a, b, c, x[i+11], 10, -1120210379);"
					+ "c = md5_ii(c, d, a, b, x[i+ 2], 15,  718787259);"
					+ "b = md5_ii(b, c, d, a, x[i+ 9], 21, -343485551);"
					+ ""
					+ "a = safe_add(a, olda);"
					+ "b = safe_add(b, oldb);"
					+ "c = safe_add(c, oldc);"
					+ "d = safe_add(d, oldd);"
					+ "}"
					+ "return Array(a, b, c, d);"
					+ "}"
					+ ""
					+ "function md5_cmn(q, a, b, x, s, t)"
					+ "{"
					+ "return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s),b);"
					+ "}"
					+ "function md5_ff(a, b, c, d, x, s, t)"
					+ "{"
					+ "return md5_cmn((b & c) | ((~b) & d), a, b, x, s, t);"
					+ "}"
					+ "function md5_gg(a, b, c, d, x, s, t)"
					+ "{"
					+ "return md5_cmn((b & d) | (c & (~d)), a, b, x, s, t);"
					+ "}"
					+ "function md5_hh(a, b, c, d, x, s, t)"
					+ "{"
					+ "return md5_cmn(b ^ c ^ d, a, b, x, s, t);"
					+ "}"
					+ "function md5_ii(a, b, c, d, x, s, t)"
					+ "{"
					+ "return md5_cmn(c ^ (b | (~d)), a, b, x, s, t);"
					+ "}"
					+ ""
					+ "function core_hmac_md5(key, data)"
					+ "{"
					+ "var bkey = str2binl(key);"
					+ "if(bkey.length > 16) bkey = core_md5(bkey, key.length * chrsz);"
					+ "var ipad = Array(16), opad = Array(16);"
					+ "for(var i = 0; i < 16; i++)"
					+ "{"
					+ "ipad[i] = bkey[i] ^ 0x36363636;"
					+ "opad[i] = bkey[i] ^ 0x5C5C5C5C;"
					+ "}"
					+ ""
					+ "var hash = core_md5(ipad.concat(str2binl(data)), 512 + data.length * chrsz);"
					+ "return core_md5(opad.concat(hash), 512 + 128);"
					+ "}"
					+ ""
					+ "function safe_add(x, y)"
					+ "{"
					+ "var lsw = (x & 0xFFFF) + (y & 0xFFFF);"
					+ "var msw = (x >> 16) + (y >> 16) + (lsw >> 16);"
					+ "return (msw << 16) | (lsw & 0xFFFF);"
					+ "}"
					+ ""
					+ "function bit_rol(num, cnt)"
					+ "{"
					+ "return (num << cnt) | (num >>> (32 - cnt));"
					+ "}"
					+ ""
					+ "function str2binl(str)"
					+ "{"
					+ "var bin = Array();"
					+ "var mask = (1 << chrsz) - 1;"
					+ "for(var i = 0; i < str.length * chrsz; i += chrsz)"
					+ "bin[i>>5] |= (str.charCodeAt(i / chrsz) & mask) << (i%32);"
					+ "return bin;"
					+ "}"
					+ ""
					+ "function binl2str(bin)"
					+ "{"
					+ "var str = \"\";"
					+ "var mask = (1 << chrsz) - 1;"
					+ "for(var i = 0; i < bin.length * 32; i += chrsz)"
					+ "str += String.fromCharCode((bin[i>>5] >>> (i % 32)) & mask);"
					+ "return str;"
					+ "}"
					+ ""
					+ "function binl2hex(binarray)"
					+ "{"
					+ "var hex_tab = hexcase ? \"0123456789ABCDEF\" : \"0123456789abcdef\";"
					+ "var str = \"\";"
					+ "for(var i = 0; i < binarray.length * 4; i++)"
					+ "{"
					+ "str += hex_tab.charAt((binarray[i>>2] >> ((i%4)*8+4)) & 0xF)+hex_tab.charAt((binarray[i>>2] >> ((i%4)*8  )) & 0xF);"
					+ "}"
					+ "return str;"
					+ "}"
					+ ""
					+ "function binl2b64(binarray)"
					+ "{"
					+ "var tab = \"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\";"
					+ "var str = \"\";"
					+ "for(var i = 0; i < binarray.length * 4; i += 3)"
					+ "{"
					+ "var triplet = (((binarray[i   >> 2] >> 8 * ( i   %4)) & 0xFF) << 16)"
					+ "| (((binarray[i+1 >> 2] >> 8 * ((i+1)%4)) & 0xFF) << 8 )"
					+ "|  ((binarray[i+2 >> 2] >> 8 * ((i+2)%4)) & 0xFF);"
					+ "for(var j = 0; j < 4; j++)" + "{"
					+ "if(i * 8 + j * 6 > binarray.length * 32) str += b64pad;"
					+ "else str += tab.charAt((triplet >> 6*(3-j)) & 0x3F);"
					+ "}" + "}" + "return str;" + "};");
			Invocable invoke = (Invocable) engine; // 调用merge方法，并传入两个参数
			// 第一个参数为,调用的方法名;插页的参数为多个参数,如:invoke.invokeFunction("方法名","参数1","参数2");
			return invoke.invokeFunction("hex_md5", data).toString();
		} catch (Exception e) {
			throw new RuntimeException("网银加密失败");
		}
	}
    public static void main(String[] args)throws Exception {  
    	
    	String params1 = "MERCHANTID=105421000002199&BRANCHID=360000000&POSID=001176031&ORDERDATE=&BEGORDERTIME=&ENDORDERTIME=&BEGORDERID=200LmAc9d6BrjqPU7f21Mk&ENDORDERID=200LmAc9d6BrjqPU7f21Mk&QUPWD=";
			String params2 = "&TXCODE=410405&SEL_TYPE=3&OPERATOR=";
			String MAC = ccbMd5(params1+params2);
			String queryParams = params1 + "bjzllh1234" + params2 + "&MAC=" + MAC;
			
			//创建URL，协议、ip、端口、访问的页面  
			URL url1 = new URL(REQUEST_URL);
			//创建HttpURLConnection 对象  
			HttpURLConnection conn=(HttpURLConnection)url1.openConnection();  
			//设置请求方式POST  
			conn.setRequestMethod("POST");  
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Accept-Charset", CHARSET);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "GB2312"));
			writer.write(queryParams);
			writer.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), CHARSET));
			// 得到HTTP响应码
			int code = conn.getResponseCode();
			System.out.println(code);
			System.out.println("==========订单查询返回报文 ==========");
			// 获取返回报文
			String line = "";
			while (!((line = reader.readLine()) == null))
				System.out.println(line);
    }  
}  
