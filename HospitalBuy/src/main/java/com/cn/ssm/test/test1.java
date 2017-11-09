package com.cn.ssm.test;

import java.math.BigDecimal;

public class test1 {

	public static void main(String[] args) {
		BigDecimal decimal = new BigDecimal(10.00);
		System.out.println(decimal);
	}
	
	public static void aa(BigDecimal decimal){
		System.out.println(decimal);
		decimal = decimal.subtract(new BigDecimal(2));
	}
	
	public static void bb(String bb){
		bb = "aa";
		System.out.println(bb);
	}

}
