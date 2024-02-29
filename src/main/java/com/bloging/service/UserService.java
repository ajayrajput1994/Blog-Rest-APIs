package com.bloging.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bloging.entity.User;

@Service
public interface UserService {
	
	public User createUser(User u);
	
	public List<User> getAllUsers();
	
	public User getUser(long id);
	
	public User updateUser(User u,long id);
	
	public Boolean delete(long id);
	
	public User findUser(String email);
}
