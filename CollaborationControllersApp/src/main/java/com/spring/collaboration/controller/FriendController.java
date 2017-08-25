package com.spring.collaboration.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.collaboration.domain.Friend;
import com.spring.collaboration.domain.User;
import com.spring.collaboration.service.FriendService;
import com.spring.collaboration.service.UserService;

@RestController
public class FriendController {

	private static final Logger log = LoggerFactory.getLogger(FriendController.class);

	@Autowired
	private Friend friend;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	private boolean isUserExist(String id)
	{
		if(userService.getById(id)==null)
			return false;
		else
			return true;
	}
	
	
	
	@GetMapping("/sendRequest-{id}")
	public ResponseEntity<Friend> addFriend(@PathVariable("id") String friendID)
	{
		if(session.getAttribute("username")==null)
		{
			log.info("NOT LOGGED IN");
			friend = new Friend();
			friend.setErrorCode("100");
			friend.setErrorMessage("Please Login");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		
		boolean check = isUserExist(friendID);
		if(check)
		{
			log.info("Adding Friend");
			User user = userService.getById(session.getAttribute("username").toString());
			User friendData = userService.getById(friendID);
			friend.setUserID(session.getAttribute("username").toString());
			friend.setFriendID(friendID);
			friend.setStatus('P');
			friend.setUserFName(user.getFirst_name());
			friend.setUserLName(user.getLast_name());
			friend.setFriendFName(friendData.getFirst_name());
			friend.setFriendLName(friendData.getLast_name());
			friend.setUserIsOnline(user.getIsOnline());
			friend.setFriendisOnline(friendData.getIsOnline());
			boolean value = friendService.save(friend);
			if(value == true)
			{
				friend.setErrorCode("200");
				friend.setErrorMessage("Friend Request sent");
				log.info("Friend Request sent");
			}
			else
			{
				friend.setErrorCode("400");
				friend.setErrorMessage("Friend Request not sent");
				log.error("Friend Requset not sent");
			}
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		
		else
		{
			log.info("FriendID not in database");
			friend = new Friend();
			friend.setErrorCode("100");
			friend.setErrorMessage("Friend Not Found");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
	}
	
	@GetMapping("/myFriends")
	public ResponseEntity<List<Friend>> getMyFriends()
	{
		if(session.getAttribute("username")==null)
		{
			log.info("NOT LOGGED IN");
			return null;
		}
		else
		{
			List<Friend> list = friendService.getFriendList(session.getAttribute("username").toString());
			log.info("List retrieved");
			if(list.isEmpty() || list == null)
			{
				log.info("List is Empty");
				return null;
			}
			else
				return new ResponseEntity<List<Friend>>(list, HttpStatus.OK);
		}
	}
	
	@GetMapping("/acceptRequest-{id}")
	public ResponseEntity<Friend> acceptFriend(@PathVariable("id") String friendID)
	{
		if(session.getAttribute("username")==null)
		{
			log.info("NOT LOGGED IN");
			friend = new Friend();
			friend.setErrorCode("100");
			friend.setErrorMessage("Please Login");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		else
		{
			log.info("Accepting Request");
			String userID = session.getAttribute("username").toString();
			boolean value = friendService.accept(userID, friendID);
			if(value)
			{
				friend.setUserID(userID);
				friend.setFriendID(friendID);
				friend.setErrorCode("200");
				friend.setErrorMessage("Friend Request Accepted");
				log.info("Request Accepted");
			}
			else
			{
				friend.setErrorCode("404");
				friend.setErrorMessage("Friend Request Not Accepted");				
				log.error("Request not accepted");
			}
			return new ResponseEntity<Friend> (friend, HttpStatus.OK);
		}
	}
	
	@GetMapping("/rejectFriend-{id}")
	public ResponseEntity<Friend> rejectFriend(@PathVariable("id") String friendID)
	{
		friend = new Friend();
		if(session.getAttribute("username")==null)
		{
			log.info("NOT LOGGED IN");
			friend.setErrorCode("100");
			friend.setErrorMessage("Please Login");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		else
		{
			try
			{
				String userID = session.getAttribute("username").toString();
				friend.setUserID(userID);
				friend.setFriendID(friendID);
				friendService.reject(userID, friendID);
				log.info("Friend Request Rejected");
				friend.setErrorCode("200");
				friend.setErrorMessage("Friend Request Rejected Success");
			}	catch(Exception ex)
			{
				friend.setErrorCode("404");
				friend.setErrorMessage("Could not reject Request");
				ex.printStackTrace();
			}
		}
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	@GetMapping("/cancelRequest-{id}")
	public ResponseEntity<Friend> cancelRequest(@PathVariable("id") String friendID)
	{
		friend = new Friend();
		if(session.getAttribute("username")==null)
		{
			log.info("NOT LOGGED IN");
			friend.setErrorCode("100");
			friend.setErrorMessage("Please Login");
			return new ResponseEntity<Friend>(friend, HttpStatus.OK);
		}
		else
		{
			try
			{
				String userID = session.getAttribute("username").toString();
				friend.setUserID(userID);
				friend.setFriendID(friendID);
				friendService.cancel(userID, friendID);
				log.info("Friend Request Cancelled");
				friend.setErrorCode("200");
				friend.setErrorMessage("Friend Request Cancelled Success");
			}	catch(Exception ex)
			{
				friend.setErrorCode("404");
				friend.setErrorMessage("Could not cancel Request");
				ex.printStackTrace();
			}
		}
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	@GetMapping("/viewPendingRequest")
	public ResponseEntity<List<Friend>> getPendingRequest()
	{
		System.out.println("enter in  view request............");
		List<Friend> list = new ArrayList<Friend>();
		if(session.getAttribute("username")==null)
		{
			log.info("NOT LOGGED IN");
			friend = new Friend();
			friend.setErrorCode("404");
			friend.setErrorMessage("You should LogIn");
			System.out.println("friend nullllllllllllllllllllllll");
			return null;
		}
		else
		{
			System.out.println("friend frist");
			log.info("Getting Pending Request for "+session.getAttribute("username").toString());
			String userID = session.getAttribute("username").toString();
			list = friendService.showPendingRequests(userID);
			log.info("Pending Requests recieved");
			if(list.isEmpty() || list==null)
			{
				friend.setUserID(null);
				friend.setErrorMessage("200");
				friend.setErrorMessage("You have no Pending Request");
				return null;
			}
			System.out.println("else.............................");
			
		}
		return new ResponseEntity<List<Friend>>(list, HttpStatus.OK);
	}

	@GetMapping("/viewSentRequest")
	public ResponseEntity<List<Friend>> getSentRequests()
	{
		List<Friend> list = new ArrayList<Friend>();
		friend = new Friend();
		if(session.getAttribute("username")==null)
		{
			log.info("NOT LOGGED IN");
			friend.setErrorCode("404");
			friend.setErrorMessage("You should LogIn");
			return null;
		}
		else
		{
			log.info("Getting Sent Request for "+session.getAttribute("username").toString());
			String userID = session.getAttribute("username").toString();
			list = friendService.viewSentRequests(userID);
			log.info("Sent Requests recieved");
			if(list.isEmpty() || list==null)
			{
				friend.setUserID(userID);
				friend.setErrorMessage("200");
				friend.setErrorMessage("You have no Pending Request");
				return null;
			}
		}
		return new ResponseEntity<List<Friend>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/removeFriend-{id}")
	public ResponseEntity<Friend> removeFriend(@PathVariable("id") String friendID)
	{
		log.info("Entering remove friend");
		if(session.getAttribute("username")==null)
		{
			log.info("NOT LOGGED IN");
			friend.setErrorCode("404");
			friend.setErrorMessage("You should LogIn");
			return null;
		}	
		else
		{
			String userID = session.getAttribute("username").toString();
			log.info("Removing friend "+userID+" and "+friendID);
			boolean value = friendService.removeFriend(userID, friendID);
			if(value)
			{
				friend = new Friend();
				friend.setErrorMessage("200");
				friend.setErrorMessage("Friend has been removed");
				log.info("Success Removing Friend");
				return new ResponseEntity<Friend>(friend, HttpStatus.OK);
			}
		}
		return null;
	}
}
