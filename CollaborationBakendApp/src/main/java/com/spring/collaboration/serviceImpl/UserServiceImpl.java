package com.spring.collaboration.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.collaboration.dao.UserDao;
import com.spring.collaboration.domain.User;
import com.spring.collaboration.service.UserService;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getAllUser() {

		return userDao.getAllUser();
	}

	public User getById(String id) {
		return userDao.getById(id);
	}
	
	public User getByemailId(String emailId){
		return userDao.getByemailId(emailId);
	}

	public boolean saveUser(User user) {
		return userDao.saveUser(user);
	}

	public boolean updateUser(User user) {
		 return userDao.updateUser(user);
	}

	public User validateUser(String userName, String password) {
		return userDao.validateUser(userName, password);
	}

}
