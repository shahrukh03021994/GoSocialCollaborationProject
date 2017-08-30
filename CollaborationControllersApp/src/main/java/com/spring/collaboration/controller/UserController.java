package com.spring.collaboration.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.collaboration.domain.User;
import com.spring.collaboration.service.FriendService;
import com.spring.collaboration.service.UserService;
import com.spring.collaboration.util.Date_Time;

@RestController
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	FriendService friendService;
	@Autowired
	private User user;
	@Autowired
	
	HttpSession session;
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public void save(@RequestBody User user) {
		userService.saveUser(user);
		
	}
	
	/*@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) 
	{
		log.debug("-----Starting the method register");

		if (userService.getById(user.getUserId()) != null) {
			user.setErrorCode("404");
			user.setErrorMessage("With this is Id the record is already exist. Please Choose another id");

		} else {
			user.setStatus('N');
			user.setIsOnline('N');
			log.debug("------saving user in controller");
			if (userService.saveUser(user)) {
				user.setErrorCode("200");
				user.setErrorMessage("Your registration is Successfull");

			} else {
				user.setErrorCode("405");
				user.setErrorMessage("Unable to process your registration. Please Contact Admin");

			}
		}
		log.debug("------Ending of the method createuser");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}*/
	
	//@RequestMapping(value="/add_User",method=RequestMethod.POST, produces = "application/json")
	@PostMapping("/add_User")
	public ResponseEntity<User> addUser(@RequestBody User user) 
	{	    
	    user.setStatus('N');
	    user.setIsOnline('N');
		boolean value = userService.saveUser(user);
		if (value == true) 
		{
			user.setErrorCode("200");
			user.setErrorMessage("User added Successfully");
		} 
		else 
		{
			user.setErrorCode("100");
			user.setErrorMessage("Add User Failed");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	
	
	
	@SuppressWarnings("unused")
	@PostMapping("/login")
	public ResponseEntity<User> validateUser(@RequestBody User user) 
	{
		System.out.println("Name - "+user.getUsername());
//		System.out.println("Password "+user.getPassword());
		User value = userService.validateUser(user.getUsername(), user.getPassword());
		System.out.println(value.getStatus());
		System.out.println("Status " +user.getStatus());
		System.out.println(user.getPassword()+" "+user.getUsername()+"  ----- "  +value.getPassword()+"  "+value.getUsername());
		if(user.getUsername().equals(value.getUsername())==true )
		{
		
		if (user.getPassword().equals(value.getPassword())==true )
		{
			System.out.println("nulll.....AAAAAAAAAAAAAAAAAAAAAAAAA.......");

			user = userService.getById(user.getUsername());
			user.setIsOnline('Y');
			Date_Time dt = new Date_Time();
			user.setLast_seen(dt.getDateTime());
			userService.saveUser(user);
			friendService.setUsersOnline(user.getUsername());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("role", user.getRole());
			session.setAttribute("isLoggedIn", "true");
			session.setAttribute("status", user.getStatus());
			if(user.getDob()!=null)
				user.setBirthdate( dt.toStringDate(user.getDob()));
			
			user.setErrorCode("200");
			user.setErrorMessage("Success");
			System.out.println("Name = "+session.getAttribute("username").toString());
			System.out.println("Role = "+session.getAttribute("role").toString());
			System.out.println(session.getAttribute("status").toString());
		}
		else
		{
			//user = new User();
			value.setErrorCode("407");
			value.setErrorMessage("Wrong username or password.");
			System.out.println("nulll............");
		
		}
		}
		else
		{
			if(value.getStatus()=='R')
			{		
				System.out.println("nulll             rrrrrrr............");

				//user = new User();
				value.setErrorCode("404");
				value.setErrorMessage("Registeration is rejected. Please Contact Admin");

			}
			else if(value.getStatus()=='N')
			{
				System.out.println("nulll.....nnnnnnnnnnnnnnnnnnnnn.......");

				//user = new User();
				value.setErrorCode("404");
				value.setErrorMessage("Registeration approval is pending. Please try again later");

			}
			else
			{
				value.setErrorCode("408");
				value.setErrorMessage("Not a Registerd User");
				System.out.println("user null condition............");
			}
		}

		return new ResponseEntity<User>(value, HttpStatus.OK);
	}
	
	
	@GetMapping("/logout")
	public ResponseEntity<User> logout()
	{
		log.info("isLoggedIN - "+session.getAttribute("isLoggedIn"));
		if(session.getAttribute("isLoggedIn") != null)
		{
			user = userService.getById(session.getAttribute("username").toString());
			user.setIsOnline('N');
			Date_Time dt = new Date_Time();
			user.setLast_seen(dt.getDateTime());
			userService.saveUser(user);
			friendService.setUsersOffline(session.getAttribute("username").toString());
			user = new User();
			user.setErrorCode("200");
			user.setErrorMessage("You have logged out.");
			session.invalidate();
		}
		else
		{
			user = new User();
			user.setErrorCode("500");
			user.setErrorMessage("You have not logged in");
			log.info(user.getErrorMessage());
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	
	
	
	
	@GetMapping("/getUserList")
	public ResponseEntity<List<User>> getUserList() throws NullPointerException
	{
			List<User> list = userService.getAllUser();
			if (list.isEmpty()) 
			{
				user.setErrorCode("100");
				user.setErrorMessage("Users are not available");
			}
			else
			{
				DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
				for(User user : list)
				{
					user.setErrorCode("200");
					user.setErrorMessage("Success");
					if(user.getDob() != null)
						user.setBirthdate(df.format(user.getDob()));
				}
			}
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	
	@PostMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user)
	{
		if(user != null)
		{
			boolean value = userService.saveUser(user);
			if (value == true) 
			{
				user.setErrorCode("200");
				user.setErrorMessage("User updated Successfully");
			} 
			else 
			{
				user.setErrorCode("100");
				user.setErrorMessage("Add User Failed");
				return null;
			}
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/getUser-{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") String userName) {
		user = userService.getById(userName);

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("User " + userName + " is not found.");
		}
		user.setErrorCode("200");
		user.setErrorMessage("User " + userName + " is found.");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}


}
	
	


