package com.spring.collaboration.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.collaboration.dao.FriendDao;
import com.spring.collaboration.domain.Friend;
import com.spring.collaboration.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService{

	@Autowired
	FriendDao friendDao;
	
	public List<Friend> getFriendList(String username) {
		// TODO Auto-generated method stub
		return friendDao.getFriendList(username);
	}

	public boolean get(String userID, String friendID) {
		// TODO Auto-generated method stub
		return friendDao.get(userID, friendID);
	}

	public boolean save(Friend friend) {
		// TODO Auto-generated method stub
		return friendDao.save(friend);
	}

	public boolean accept(String userID, String friendID) {
		// TODO Auto-generated method stub
		return friendDao.accept(userID, friendID);
	}

	public boolean reject(String userID, String friendID) {
		// TODO Auto-generated method stub
		return friendDao.reject(userID, friendID);
	}

	public boolean cancel(String userID, String friendID) {
		// TODO Auto-generated method stub
		return friendDao.cancel(userID, friendID);
	}

	public boolean removeFriend(String userID, String friendID) {
		// TODO Auto-generated method stub
		return friendDao.removeFriend(userID, friendID);
	}

	public List<Friend> showPendingRequests(String userID) {
		// TODO Auto-generated method stub
		return friendDao.showPendingRequests(userID);
	}

	public List<Friend> viewSentRequests(String username) {
		// TODO Auto-generated method stub
		return friendDao.viewSentRequests(username);
	}

	public boolean setUsersOnline(String username) {
		// TODO Auto-generated method stub
		return friendDao.setUsersOnline(username);
	}

	public boolean setUsersOffline(String username) {
		// TODO Auto-generated method stub
		return friendDao.setUsersOffline(username);
	}

	
}
