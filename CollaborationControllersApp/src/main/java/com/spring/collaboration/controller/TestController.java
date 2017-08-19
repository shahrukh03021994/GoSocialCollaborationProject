package com.spring.collaboration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.collaboration.domain.User;
import com.spring.collaboration.service.UserService;

@Controller
public class TestController {

	@Autowired
	UserService userService;
	@Autowired
	User user;
	@RequestMapping("/test")
	public String test() {
		/*user.setfName("khan");
		user.setlName("khan");
		user.setAddress("khan");
		user.setEmailId("khan");
		user.setPassword("khan");
		user.setPhoneNo("454");
		user.setIsOnline('N');
		user.setReason("nooo");
		user.setRole("USER");
		user.setStatus('A');*/
		
		userService.saveUser(user);
		return "test";
		
		//return "test";
	}
}
