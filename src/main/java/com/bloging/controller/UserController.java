package com.bloging.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloging.entity.User;
import com.bloging.entity.UserActivities;
import com.bloging.helper.MailSender;
import com.bloging.service.UserActivitesService;
import com.bloging.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private MailSender mailSender;
	@Autowired
	private UserService userService;
	@Autowired
	private UserActivitesService userActivService;
	@PostMapping("/add")
	public ResponseEntity<User> createUser(@RequestBody User user){
		try {
			User user2=userService.createUser(user);
		UserActivities ua=	userActivService.saveActivities(user2);
		mailSender.sendMail(user2.getEmail(), "verify account",user2.getName(), ua.getActive_id().toString());
			return new ResponseEntity<>(user2,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/all")
	public ResponseEntity<List<User>> getUsers(){
		List<User> users=userService.getAllUsers();
		if (!users.isEmpty() && users!=null) {
			return new ResponseEntity<>(users,HttpStatus.OK);
		}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable long id){
		User user=userService.getUser(id);
		if(user!=null){
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable long id){
		boolean f=userService.delete(id);
		if (f==true) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable long id){
		User u=userService.updateUser(user,id);
		
		if (u!=null) {
			//
			return new ResponseEntity<>(u,HttpStatus.OK);
		}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/verify/{id}")
	public ResponseEntity<?> verifyUser(@PathVariable UUID id){
	boolean f=userActivService.updateActivities(id);
		if (f) {
			return new ResponseEntity<>("Verified",HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
}
