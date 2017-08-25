package com.spring.collaboration.service;

import java.util.List;

import com.spring.collaboration.domain.Blog;

public interface BlogService {
	public boolean addBlog(Blog blog);


public boolean approveBlog(Blog blog);
	
	public boolean updateBlog(Blog blog);	
	
	public boolean deleteBlog(Blog blog);
	
	public Blog getBlog(String title);
	
	public List<Blog> getBlogByUser(String username);
	
	public List<Blog> getApprovedBlogs();
	
	public List<Blog> getAllBlogs();
}
