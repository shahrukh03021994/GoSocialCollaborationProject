package com.spring.collaboration.service;

import java.util.List;

import com.spring.collaboration.domain.User;

public interface UserService {
	List<User> getAllUser();

	User getById(String id);
	User getByemailId(String emailId);

	boolean saveUser(User user);

	boolean updateUser(User user);
	
	public User validateUser(String userName, String password);

}
