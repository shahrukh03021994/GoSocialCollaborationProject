package com.spring.collaboration.service;

import java.util.List;

import com.spring.collaboration.domain.BlogComment;

public interface BlogCommentService {


	public boolean addBlogComment(BlogComment blogComment);

	public boolean deleteComment(int id);
	
	public List<BlogComment> getBlogComments(String blog_id);
}
