package com.bloging.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bloging.entity.Blog;
@Service
public interface BlogService {

	public List<Blog> getBlogs(String username);
	
	public Blog createBlog(Blog blog,String username);
	
	public Blog getOneBlog(int id);
	
	public Blog updateBlog(Blog blog,int id,String username);
	
	public Boolean deleteBlog(int id,String username);
	
}
