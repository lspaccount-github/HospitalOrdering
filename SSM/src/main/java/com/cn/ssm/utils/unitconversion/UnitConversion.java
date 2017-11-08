package com.cn.ssm.utils.unitconversion;

/**
 * 单位换算
 * @author Administrator
 *
 */
public class UnitConversion{

	/** 厘米、分米、米、千米、毫米、微米、纳米*/
	public static final String[] lengths={"cm","dm","m","km","mm","μm","nm"};
	
	/** 吨、千克、克、斤、两*/
	public static final String[] weights={"t","kg","g","gongjin","jin","liang"};
	
	/** 升、毫升、立方米、立方分米*/
	public static final String[] volumes={"l","ml","m³","dm³"};
	
	public static void main(String[] args) {
		System.out.println(UnitConversion.getConversionValue(1.0, "liang", "jin", 1));
	}
	
	/**
	 * 单位转换
	 * @param number 数值
	 * @param curUnit 待转换单位名称
	 * @param tarUnit 目标单位名称
	 * @param unit 单位类型(0:长度转换,1:重量转换,3:体积转换)
	 * @return
	 */
	public static double getConversionValue(double number,String curUnit,String tarUnit,int unit){
		
		switch (unit) {
		case 0:
			return lengthConversion(number, curUnit, tarUnit);
		case 1:
			return weightConversion(number, curUnit, tarUnit);
		case 2:
			return volumeConversion(number, curUnit, tarUnit);
		default:
			throw new RuntimeException("不支付该单位转换");
		}
	}
	
	/**
	 * 长度转换
	 * @param number
	 * @param curUnit
	 * @param tarUnit
	 * @return
	 */
	public static double lengthConversion(double number,String curUnit,String tarUnit){
		
		int curIndex = -1,tarIndex = -1;
		for (int i = 0; i < lengths.length; i++) {
			if(lengths[i].equals(curUnit)){
				curIndex = i;
			}else if(lengths[i].equals(tarUnit)){
				tarIndex = i;
			}
			if(curIndex!=-1&&tarIndex!=-1){
				break;
			}
		}
		
		if(curIndex<0 || tarIndex<0){
			throw new RuntimeException("无法转换该单位");
		}
		
		if(curIndex==tarIndex){return number;}
		else if(curIndex==0&&tarIndex==1){number/=10;}
		else if(curIndex==0&&tarIndex==2){number/=100;}
		else if(curIndex==0&&tarIndex==3){number/=100*1000;}
		else if(curIndex==0&&tarIndex==4){number*=10;}
		else if(curIndex==0&&tarIndex==5){number*=10*1000;}
		else if(curIndex==0&&tarIndex==6){number*=10*1000*1000;}
		
		//***************
		else if(curIndex==1&&tarIndex==0){number*=10;}
		else if(curIndex==1&&tarIndex==2){number/=10;}
		else if(curIndex==1&&tarIndex==3){number/=10*1000;}
		else if(curIndex==1&&tarIndex==4){number*=100;}
		else if(curIndex==1&&tarIndex==5){number*=100*1000;}
		else if(curIndex==1&&tarIndex==6){number*=100*1000*1000;}
		
		//************
		else if(curIndex==2&&tarIndex==0){number*=100;}
		else if(curIndex==2&&tarIndex==1){number*=10;}
		else if(curIndex==2&&tarIndex==3){number/=1000;}
		else if(curIndex==2&&tarIndex==4){number*=1000;}
		else if(curIndex==2&&tarIndex==5){number*=1000*1000;}
		else if(curIndex==2&&tarIndex==6){number*=1000*1000*1000;}
		
		//********************
		else if(curIndex==3&&tarIndex==0){number*=100*1000;}
		else if(curIndex==3&&tarIndex==1){number*=10*1000;}
		else if(curIndex==3&&tarIndex==2){number*=1000;}
		else if(curIndex==3&&tarIndex==4){number*=1000*1000;}
		else if(curIndex==3&&tarIndex==5){number*=1000*1000*1000;}
		else if(curIndex==3&&tarIndex==6){number*=1000*1000*1000*1000;}
		
		//***********************
		else if(curIndex==4&&tarIndex==0){number/=10;}
		else if(curIndex==4&&tarIndex==1){number/=100;}
		else if(curIndex==4&&tarIndex==2){number/=1000;}
		else if(curIndex==4&&tarIndex==3){number/=1000*1000;}
		else if(curIndex==4&&tarIndex==5){number*=1000;}
		else if(curIndex==4&&tarIndex==6){number*=1000*1000;}
		
		//************************
		else if(curIndex==5&&tarIndex==0){number/=10*1000;}
		else if(curIndex==5&&tarIndex==1){number/=100*1000;}
		else if(curIndex==5&&tarIndex==2){number/=1000*1000;}
		else if(curIndex==5&&tarIndex==3){number/=1000*1000*1000;}
		else if(curIndex==5&&tarIndex==4){number/=1000;}
		else if(curIndex==5&&tarIndex==6){number*=1000;}
		
		//****************************
		else if(curIndex==6&&tarIndex==0){number/=10*1000*1000;}
		else if(curIndex==6&&tarIndex==1){number/=100*1000*1000;}
		else if(curIndex==6&&tarIndex==2){number/=1000*1000*1000;}
		else if(curIndex==6&&tarIndex==3){number/=1000*1000*1000*1000;}
		else if(curIndex==6&&tarIndex==4){number/=1000*1000;}
		else if(curIndex==6&&tarIndex==5){number/=1000;}
		return number;
	} 
	
	/**
	 * 重量转换
	 * @param number
	 * @param curUnit
	 * @param tarUnit
	 * @return
	 */
	public static double weightConversion(double number,String curUnit,String tarUnit){
		int curIndex = -1,tarIndex = -1;
		for (int i = 0; i < weights.length; i++) {
			if(weights[i].equals(curUnit)){
				curIndex = i;
			}else if(weights[i].equals(tarUnit)){
				tarIndex = i;
			}
			if(curIndex!=-1&&tarIndex!=-1){
				break;
			}
		}
		
		if(curIndex<0 || tarIndex<0){
			throw new RuntimeException("无法转换该单位");
		}
		
		if(curIndex==tarIndex){return number;}
		else if(curIndex==0){
			if(tarIndex==1){number*=1000;}
			else if(tarIndex==2){number*=1000*1000;}
			else if(tarIndex==3){number*=1000;}
			else if(tarIndex==4){number*=2*1000;}
			else if(tarIndex==5){number*=2*1000*10;}
		}else if(curIndex==1){
			if(tarIndex==0){number/=1000;}
			else if(tarIndex==2){number*=1000;}
			else if(tarIndex==3){number*=1;}
			else if(tarIndex==4){number*=2;}
			else if(tarIndex==5){number*=2*10;}
		}else if(curIndex==2){
			if(tarIndex==0){number/=1000*1000;}
			else if(tarIndex==1){number/=1000;}
			else if(tarIndex==3){number/=1000;}
			else if(tarIndex==4){number/=500;}
			else if(tarIndex==5){number/=50;}
		}
		else if(curIndex==3){
			if(tarIndex==0){number/=1000;}
			else if(tarIndex==1){number*=1;}
			else if(tarIndex==2){number*=1000;}
			else if(tarIndex==4){number*=2;}
			else if(tarIndex==5){number*=2*10;}
		}else if(curIndex==4){
			if(tarIndex==0){number/=2*1000;}
			else if(tarIndex==1){number/=2;}
			else if(tarIndex==2){number*=500;}
			else if(tarIndex==3){number/=2;}
			else if(tarIndex==5){number*=10;}
		}
		else if(curIndex==5){
			if(tarIndex==0){number/=2*1000*10;}
			else if(tarIndex==1){number/=20;}
			else if(tarIndex==2){number*=50;}
			else if(tarIndex==3){number/=20;}
			else if(tarIndex==4){number/=10;}
		}
		return number;
	} 

	/**
	 * 体积转换
	 * @param number
	 * @param curUnit
	 * @param tarUnit
	 * @return
	 */
	public static double volumeConversion(double number,String curUnit,String tarUnit){
		int curIndex = -1,tarIndex = -1;
		for (int i = 0; i < volumes.length; i++) {
			if(volumes[i].equals(curUnit)){
				curIndex = i;
			}else if(volumes[i].equals(tarUnit)){
				tarIndex = i;
			}
			if(curIndex!=-1&&tarIndex!=-1){
				break;
			}
		}
		
		if(curIndex<0 || tarIndex<0){
			throw new RuntimeException("无法转换该单位");
		}
		
		if(curIndex==tarIndex){return number;}
		else if(curIndex==0){
			if(tarIndex==1){number*=1000;}
			else if(tarIndex==2){number/=1000;}
			else if(tarIndex==3){number*=1;}
		}else if(curIndex==1){
			if(tarIndex==0){number/=1000;}
			else if(tarIndex==2){number/=1000*1000;}
			else if(tarIndex==3){number/=1000;}
		}else if(curIndex==2){
			if(tarIndex==0){number*=1000;}
			else if(tarIndex==1){number*=1000*1000;}
			else if(tarIndex==3){number*=1000;}
		}
		else if(curIndex==3){
			if(tarIndex==0){number*=1;}
			else if(tarIndex==1){number*=1000;}
			else if(tarIndex==2){number/=1000;}
		}
		return number;
	}

}
