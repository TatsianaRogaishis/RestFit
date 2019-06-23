package com.restfit.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.restfit.dto.UserRegistrationDto;
import com.restfit.model.User;


public interface UserService {
	  
	 public User findUserByLogin(String login);
	 
	 public void saveUser(User user);
	}