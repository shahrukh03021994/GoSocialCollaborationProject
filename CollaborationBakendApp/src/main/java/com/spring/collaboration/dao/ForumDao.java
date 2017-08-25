package com.spring.collaboration.dao;

import java.util.List;

import com.spring.collaboration.domain.Forum;

public interface ForumDao {

public boolean addForum(Forum forum);
	
	public boolean deleteForum(int id);
	
	public boolean updateForum(Forum forum);
	
	public Forum getForum(int id);
	
	public List<Forum> getUserForums(String username);
	
	public List<Forum> getForumList();
	
	public List<Forum> approvedForums();
}
