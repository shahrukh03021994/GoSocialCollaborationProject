package com.spring.collaboration.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.collaboration.dao.BlogDao;
import com.spring.collaboration.domain.Blog;
import com.spring.collaboration.service.BlogService;

@Service
public class BlogServiceImpl  implements BlogService{

	@Autowired
	BlogDao blogDao;
	
	public boolean addBlog(Blog blog) {
		
		return blogDao.addBlog(blog);
	}

	public boolean approveBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.approveBlog(blog);
	}

	public boolean updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.updateBlog(blog);
	}

	public boolean deleteBlog(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.deleteBlog(blog);
	}

	public Blog getBlog(String title) {
		// TODO Auto-generated method stub
		return blogDao.getBlog(title);
	}

	public List<Blog> getBlogByUser(String username) {
		// TODO Auto-generated method stub
		return blogDao.getBlogByUser(username);
	}

	public List<Blog> getApprovedBlogs() {
		// TODO Auto-generated method stub
		return blogDao.getApprovedBlogs();
	}

	public List<Blog> getAllBlogs() {
		// TODO Auto-generated method stub
		return blogDao.getAllBlogs();
	}

}
