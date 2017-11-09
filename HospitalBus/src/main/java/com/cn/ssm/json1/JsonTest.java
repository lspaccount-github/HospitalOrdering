package com.cn.ssm.json1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTest {

    public static void main(String[] args) {
        /*new JsonTest().test1();
        new JsonTest().test2();
        new JsonTest().test3();
        new JsonTest().test4();
        new JsonTest().test5();
        new JsonTest().test6();
        new JsonTest().test7();
        new JsonTest().test8();*/
    	new JsonTest().test9();
    }

    /**
     * JSON字符串转JSONObject对象
     */
    public void test1() {
        String jsonStr = "{\"name\":\"ZhangSan\",\"sex\":\"boy\",\"age\":18}";
        JSONObject jsonObj = JSONObject.fromObject(jsonStr);
        System.out.println(jsonObj.toString());
    }

    /**
     * 简单JSONObject对象转java对象
     */
    public void test2() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", "LiSi");
        jsonObj.put("sex", "girl");
        jsonObj.put("age", 17);
        Student student = (Student) JSONObject.toBean(jsonObj, Student.class);
        System.out.println(student.getName() + " | " + student.getSex() + " | " + student.getAge());
    }

    /**
     * 复杂JSONObject对象转java对象
     */
    @SuppressWarnings("rawtypes")
    public void test3() {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("ZhangSan", "boy", 18));
        students.add(new Student("LiSi", "girl", 17));

        BanJi banji = new BanJi();
        banji.setBanJiName("日语二班");
        banji.setStudents(students);

        JSONObject jsonObj = JSONObject.fromObject(banji);
        Map<String, Class> classMap = new HashMap<String, Class>();
        classMap.put("students", Student.class);
        BanJi banji2 = (BanJi) JSONObject.toBean(jsonObj, BanJi.class, classMap);
        System.out.println(banji2.getStudents().get(0).getName()); // 验证转换是否成功
    }

    /**
     * 简单java集合对象转JSONArray
     */
    public void test4() {
        List<Student> students = new ArrayList<Student>();
        students.add(new Student("ZhangSan", "boy", 18));
        students.add(new Student("LiSi", "girl", 17));
        JSONArray jsonArray = JSONArray.fromObject(students);
        System.out.println(jsonArray.toString());
    }

    /**
     * JSONArray转java集合对象
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
    public void test5() {
        JSONObject jsonObj1 = new JSONObject();
        jsonObj1.put("name", "ZhangSan");
        jsonObj1.put("sex", "boy");
        jsonObj1.put("age", 18);

        JSONObject jsonObj2 = new JSONObject();
        jsonObj2.put("name", "lisi");
        jsonObj2.put("sex", "girl");
        jsonObj2.put("age", 17);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, jsonObj1);
        jsonArray.add(1, jsonObj2);

        List<Student> students3 = JSONArray.toList(jsonArray, Student.class);
        System.out.println(students3.get(0).getName());
        System.out.println(students3.get(1).getName());
    }

    /**
     * 复杂java集合对象转JSONArray
     */
    @SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
    public void test6() {
        BanJi banji1 = new BanJi();
        banji1.setBanJiName("日语一班");
        List<Student> students1 = new ArrayList<Student>();
        students1.add(new Student("ZhangSan", "boy", 18));
        banji1.setStudents(students1);

        BanJi banji2 = new BanJi();
        banji2.setBanJiName("日语二班");
        List<Student> students2 = new ArrayList<Student>();
        students1.add(new Student("LiSi", "girl", 17));
        banji2.setStudents(students2);

        List<BanJi> banjiList = new ArrayList<BanJi>();
        banjiList.add(banji1);
        banjiList.add(banji2);

        JSONArray jsonArray = JSONArray.fromObject(banjiList);
        Map<String, Class> classMap = new HashMap<String, Class>();
        classMap.put("students", Student.class);
        List<BanJi> banjiList2 = JSONArray.toList(jsonArray, BanJi.class, classMap);
        System.out.println(banjiList2.get(0).getStudents().get(0).getName());

    }

    /**
     * 拆分JSONArray为JSONObject
     */
    public void test7() {
        JSONObject jsonObj1 = new JSONObject();
        jsonObj1.put("name", "ZhangSan");
        jsonObj1.put("sex", "boy");
        jsonObj1.put("age", 18);

        JSONObject jsonObj2 = new JSONObject();
        jsonObj2.put("name", "lisi");
        jsonObj2.put("sex", "girl");
        jsonObj2.put("age", 17);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, jsonObj1);
        jsonArray.add(1, jsonObj2);

        JSONObject object = (JSONObject) jsonArray.get(0);
        System.out.println(object.toString());
    }
    
    public void test8() {
    	String Schools = "[{name:\"育才中学\",banJis:[{banJiName:\"语文班\",students:[{\"name\":\"ZhangSan\",\"sex\":\"boy\",\"age\":18}],courses:[{\"number\":\"001\",\"name\":\"语言课程\"}]},{banJiName:\"英语班\",students:[{\"name\":\"lisi\",\"sex\":\"boy\",\"age\":20}],courses:[{\"number\":\"002\",\"name\":\"英语课程\"}]}]}]";
    	JSONArray jsonArray1 = JSONArray.fromObject(Schools);
       
    	
    	String json = "[{banJiName:\"语文班\",students:[{\"name\":\"ZhangSan\",\"sex\":\"boy\",\"age\":18}],courses:[{\"number\":\"001\",\"name\":\"语言课程\"}]},{banJiName:\"英语班\",students:[{\"name\":\"lisi\",\"sex\":\"boy\",\"age\":20}],courses:[{\"number\":\"002\",\"name\":\"英语课程\"}]}]";
        JSONArray jsonArray = JSONArray.fromObject(json);
        for (int i = 0; i < jsonArray.size(); i++) {
        	JSONObject jsonObj = (JSONObject) jsonArray.get(i);
        	Map<String, Class> classMap = new HashMap<String, Class>();
        	classMap.put("students", Student.class);
        	classMap.put("courses", Course.class);
        	BanJi banji = (BanJi) JSONObject.toBean(jsonObj, BanJi.class, classMap);
        	System.out.println(banji.getBanJiName());
        	List<Student> students = banji.getStudents();
        	for (Student student : students) {
				System.out.println(student.toString());
			}
        	List<Course> courses = banji.getCourses();
        	for (Course course : courses) {
        		System.out.println(course.toString());
				
			}
		}
    }
    
    public void test9() {
    	String Schools = "[{name:\"育才中学\",banJis:[{banJiName:\"语文班\",students:[{\"name\":\"ZhangSan\",\"sex\":\"boy\",\"age\":18}],courses:[{\"number\":\"001\",\"name\":\"语言课程\"}]},{banJiName:\"英语班\",students:[{\"name\":\"lisi\",\"sex\":\"boy\",\"age\":20}],courses:[{\"number\":\"002\",\"name\":\"英语课程\"}]}]}]";
    	JSONArray jsonArray = JSONArray.fromObject(Schools);
    	for (int i = 0; i < jsonArray.size(); i++) {
    		JSONObject jsonObj = (JSONObject) jsonArray.get(i);
        	Map<String, Class> classMap = new HashMap<String, Class>();
        	classMap.put("banJis", BanJi.class);
        	classMap.put("students", Student.class);
        	classMap.put("courses", Course.class);
        	School school = (School) JSONObject.toBean(jsonObj, School.class, classMap);
        	System.out.println("==>"+school.getName());
        	List<BanJi> banJis = school.getBanJis();
        	for (BanJi banJi : banJis) {
        		System.out.println("==>==>"+banJi.getBanJiName());
        		List<Student> students = banJi.getStudents();
        		for (Student student : students) {
        			System.out.println("==>==>==>"+student.getName());
				}
        		List<Course> courses = banJi.getCourses();
        		for (Course course : courses) {
        			System.out.println("==>==>==>"+course.getName());
				}
			}
		}
    }

}
