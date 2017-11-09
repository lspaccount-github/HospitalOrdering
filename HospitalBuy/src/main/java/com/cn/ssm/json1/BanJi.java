package com.cn.ssm.json1;

import java.util.List;

/**
 * 班级类
 */
public class BanJi {

    private String banJiName; // 班级名

    private List<Student> students; // 学生
    
    private List<Course> courses; // 课程

    /**
     * 构造函数
     */
    public BanJi() {
        super();
    }

	public String getBanJiName() {
		return banJiName;
	}

	public void setBanJiName(String banJiName) {
		this.banJiName = banJiName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}