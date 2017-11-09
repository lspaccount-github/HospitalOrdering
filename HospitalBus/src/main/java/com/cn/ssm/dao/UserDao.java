package com.cn.ssm.dao;

import com.cn.ssm.pojo.User;

public interface UserDao {

	User selectById(int userId);
	
	int updateUser(User user);

}
