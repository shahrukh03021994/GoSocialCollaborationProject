package com.spring.collaboration.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.collaboration.dao.ForumReplayDao;
import com.spring.collaboration.domain.ForumReply;
import com.spring.collaboration.service.ForumReplyService;

@Service
public class ForumReplyServiceImpl  implements ForumReplyService{

	@Autowired
	ForumReplayDao forumReplayDao;
	
	public boolean addReply(ForumReply reply) {
		// TODO Auto-generated method stub
		return forumReplayDao.addReply(reply);
	}

	public boolean deleteReply(ForumReply reply) {
		// TODO Auto-generated method stub
		return forumReplayDao.deleteReply(reply);
	}

	public ForumReply getReply(int id) {
		// TODO Auto-generated method stub
		return forumReplayDao.getReply(id);
	}

	public List<ForumReply> getForumReply(int forum_id) {
		// TODO Auto-generated method stub
		return forumReplayDao.getForumReply(forum_id);
	}

}
