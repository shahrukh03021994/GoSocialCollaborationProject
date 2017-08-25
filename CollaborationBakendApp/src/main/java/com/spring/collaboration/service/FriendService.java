package com.spring.collaboration.service;

import java.util.List;

import com.spring.collaboration.domain.Friend;

public interface FriendService {


	public List<Friend> getFriendList(String username);

	public boolean get(String userID, String friendID);
	
	public boolean save(Friend friend);
	public boolean accept(String userID, String friendID);
	
	public boolean reject(String userID, String friendID);
	public boolean cancel(String userID, String friendID);
	public boolean removeFriend(String userID, String friendID);
	
	public List<Friend> showPendingRequests(String userID);
	public List<Friend> viewSentRequests(String username);
	
	public boolean setUsersOnline(String username);
	public boolean setUsersOffline(String username);
}
