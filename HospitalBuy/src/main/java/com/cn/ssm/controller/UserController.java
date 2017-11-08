package com.cn.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.ssm.pojo.User;
import com.cn.ssm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getUser")
	public String getUsers(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		User user = userService.getById(1);
		int aa = userService.update(user);
		System.out.println(user.toString());
		System.out.println("12345689");
		System.out.println("12345689");
		System.out.println("12345689");
		System.out.println("12345689");
		System.out.println("12345689");
		System.out.println("12345689");
        System.out.println("已解决");
        return "userList";
	}
}
