package com.cn.ssm.json1;

/**
 * 学生类
 */
public class Student {

    private String name; // 姓名

    private String sex; // 性别

    private int age; // 年龄

    /**
     * 构造函数
     */
    public Student() {
        super();
    }

    /**
     * 构造函数
     */
    public Student(String name, String sex, int age) {
        super();
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
	
}