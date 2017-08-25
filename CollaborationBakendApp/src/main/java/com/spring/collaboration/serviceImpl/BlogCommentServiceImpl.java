package com.spring.collaboration.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.collaboration.dao.BlogCommentDao;
import com.spring.collaboration.domain.BlogComment;
import com.spring.collaboration.service.BlogCommentService;


@Service
public class BlogCommentServiceImpl implements BlogCommentService {

	@Autowired
	BlogCommentDao blogCommentDao;
	
	public boolean addBlogComment(BlogComment blogComment) {
		// TODO Auto-generated method stub
		return blogCommentDao.addBlogComment(blogComment);
	}

	public boolean deleteComment(int id) {
		// TODO Auto-generated method stub
		return blogCommentDao.deleteComment(id);
	}

	public List<BlogComment> getBlogComments(String blog_id) {
		// TODO Auto-generated method stub
		return blogCommentDao.getBlogComments(blog_id);
	}

}
