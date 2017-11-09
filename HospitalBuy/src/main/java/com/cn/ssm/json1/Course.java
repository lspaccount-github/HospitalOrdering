package com.cn.ssm.json1;

/**
 * 课程
 * @author Administrator
 *
 */
public class Course {
	
	/** 课程编号*/
	private String number;
	
	/** 课程名称*/
	private String name;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Course [number=" + number + ", name=" + name + "]";
	}
}
