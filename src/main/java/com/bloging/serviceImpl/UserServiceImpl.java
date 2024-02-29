package com.bloging.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bloging.entity.User;
import com.bloging.repository.UserRepository;
import com.bloging.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(User u) {
		User user =userRepository.findByUsername(u.getEmail());
		if(user==null) {
			u.setDate(LocalDateTime.now());
		 return	userRepository.save(u);
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User updateUser(User u, long id) {
		User user=userRepository.findByUsername(u.getEmail());
		if (user!=null) {
			user.setName(u.getName()!=null && u.getName().trim().length()!=0? u.getName() : user.getName());
			user.setDate(LocalDateTime.now());
		return userRepository.save(user);
		}
		return user;
	}

	@Override
	public Boolean delete(long id) {
		User user=userRepository.findById(id).orElse(null);
		if(user!=null) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public User findUser(String email) {
		return userRepository.findByUsername(email);
	}

}
