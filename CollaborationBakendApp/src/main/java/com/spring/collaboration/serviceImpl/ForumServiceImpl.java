package com.spring.collaboration.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.collaboration.dao.ForumDao;
import com.spring.collaboration.domain.Forum;
import com.spring.collaboration.service.ForumService;

@Service
public class ForumServiceImpl implements ForumService{

	@Autowired
	ForumDao forumDao;
	
	public boolean addForum(Forum forum) {
		// TODO Auto-generated method stub
		return forumDao.addForum(forum);
	}

	public boolean deleteForum(int id) {
		// TODO Auto-generated method stub
		return forumDao.deleteForum(id);
	}

	public boolean updateForum(Forum forum) {
		// TODO Auto-generated method stub
		return forumDao.updateForum(forum);
	}

	public Forum getForum(int id) {
		// TODO Auto-generated method stub
		return forumDao.getForum(id);
	}

	public List<Forum> getUserForums(String username) {
		// TODO Auto-generated method stub
		return forumDao.getUserForums(username);
	}

	public List<Forum> getForumList() {
		// TODO Auto-generated method stub
		return forumDao.getForumList();
	}

	public List<Forum> approvedForums() {
		// TODO Auto-generated method stub
		return forumDao.approvedForums();
	}

}
