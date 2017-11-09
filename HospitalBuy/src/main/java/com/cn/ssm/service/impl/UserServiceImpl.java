package com.cn.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.ssm.dao.UserDao;
import com.cn.ssm.pojo.User;
import com.cn.ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	public User getById(int userId) {
		System.out.println(userDao);
		return userDao.selectById(userId);
	}

	@Override
	@Transactional
	public int update(User user) {
		System.out.println(user.getUsername());
		User user1 = new User();
		user1.setId(user.getId());
		user1.setUsername("222");
		int updateUser = userDao.updateUser(user1);
		System.out.println(updateUser);
		System.out.println(user.getUsername());
		User selUser = userDao.selectById(user.getId());
		System.out.println(selUser.getUsername());
		if(1==1){
			throw new RuntimeException("rollbake");
		}
		return updateUser;
	}
}
