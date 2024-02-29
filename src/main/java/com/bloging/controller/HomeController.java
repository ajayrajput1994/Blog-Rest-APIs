package com.bloging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bloging.entity.Blog;
import com.bloging.entity.User;
import com.bloging.service.BlogService;
import com.bloging.service.UserService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class HomeController {
	
	@Autowired
	 private BlogService blogService;
	
	@GetMapping("/")
	public ResponseEntity<String> home() {
		
		return ResponseEntity.ok("welcome");
	}
	@GetMapping("/blogs/{username}")
	public ResponseEntity<List<Blog>> getBlogs(@PathVariable String username){
		List<Blog> blogs=blogService.getBlogs(username);
		log.info("blogs::",blogs);
		try {
		if (!blogs.isEmpty() && blogs!=null) {
			return new ResponseEntity<>(blogs,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/blog/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable int id) {
		Blog blog=blogService.getOneBlog(id);
		if (blog!=null){
			return new ResponseEntity<>(blog,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/blog/add/{username}")
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog,@PathVariable String username){
		
		try{
			Blog blog2=blogService.createBlog(blog,username);
			return new ResponseEntity<>(blog2,HttpStatus.CREATED); 
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@PutMapping("/blog/update/{id}/{username}")
	public ResponseEntity<?> updateBlog(@RequestBody Blog blog,@PathVariable int id,@PathVariable String username) {
		Blog blog2=blogService.updateBlog(blog,id,username);
		if (blog2!=null) {
			return new ResponseEntity<>(blog2,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/blog/delete/{id}/{username}")
	public ResponseEntity<?> deleteBlog(@PathVariable int id,@PathVariable String username){
		boolean f=blogService.deleteBlog(id,username);
		if(f==true){
			return new ResponseEntity<>(HttpStatus.OK);
		}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
