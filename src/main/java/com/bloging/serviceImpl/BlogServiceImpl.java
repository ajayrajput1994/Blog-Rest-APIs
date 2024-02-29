package com.bloging.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bloging.entity.Blog;
import com.bloging.entity.User;
import com.bloging.repository.BlogRepository;
import com.bloging.repository.UserRepository;
import com.bloging.service.BlogService;
import com.bloging.service.UserService;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Blog> getBlogs(String username) {
		User user=userRepository.findByUsername(username);
		List<Blog> blogs=null;
		log.info("user ::{}",user);
		if (user!=null) {
			 blogs=user.getBlogs();
			return blogs;
			
		}
		return blogs;
	}

	@Override
	public Blog createBlog(Blog blog,String username) {
		User user=userRepository.findByUsername(username);
		log.info("User ::{}",user);
		if(blog!=null && user!=null) {
			blog.setDate(LocalDateTime.now());
			//Blog blog2=blogRepository.save(blog);
			log.info("blog ::{}",blog);
			user.getBlogs().add(blog);
			blog.setUser(user);
			userRepository.save(user);
		}
		return blog;
	}

	@Override
	public Blog getOneBlog(int id) {
		return blogRepository.findById(id).orElse(null);
	}

	@Override
	public Blog updateBlog(Blog blog,int id,String username) {
		Blog blog2=blogRepository.findById(id).orElse(null);
		User user=userRepository.findByUsername(username);
		if(blog2!=null && user !=null && (blog2.getUser().getId()==user.getId())) {
			blog2.setTitle(blog.getTitle()!=null && blog.getTitle().trim().length()==0? blog2.getTitle(): blog.getTitle());
			blog2.setDescription(blog.getDescription()!=null && blog.getDescription().trim().length()==0?blog2.getDescription():blog.getDescription());
			blog2.setDate(LocalDateTime.now());
			return blogRepository.save(blog2);	
		}
		return blog2;
	}

	@Override
	public Boolean deleteBlog(int id,String username) {
		Blog blog=blogRepository.findById(id).orElse(null);
		User user=userRepository.findByUsername(username);
		if (blog!=null && user!=null && (blog.getUser().getId()==user.getId())){
			blog.setUser(null);
			blogRepository.deleteById(id);
			return true;
		}
		return false; 
	}

}
