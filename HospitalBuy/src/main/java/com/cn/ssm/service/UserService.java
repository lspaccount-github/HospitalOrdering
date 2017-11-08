package com.cn.ssm.service;

import com.cn.ssm.pojo.User;

public interface UserService {

	User getById(int userId);

	int update(User user);

}
