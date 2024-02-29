package com.bloging.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloging.entity.User;
import com.bloging.entity.UserActivities;
import com.bloging.repository.UserActiveRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserActivitesService {
	
	@Autowired
	private UserActiveRepository uActiveRepo;
	
	@Autowired
	private UserService userService;
	
	public UserActivities saveActivities(User u) {
		UserActivities ua=new UserActivities();
			ua.setCreteDate(LocalDateTime.now());
			ua.setStatus(false);
			ua.setUser(u);
			uActiveRepo.save(ua);
			return ua;
		
	}
	
	
	public Boolean updateActivities(UUID id) {
		UserActivities ua=uActiveRepo.findById(id).get();
		log.info("activity::",ua);
		if (ua!=null) {
		User user=userService.getUser(ua.getUser().getId());
		log.info("active user ",user);
			ua.setStatus(true);
			ua.setUser(user);
			uActiveRepo.save(ua);
			return true;
		}
		return false;
		
	}
}
