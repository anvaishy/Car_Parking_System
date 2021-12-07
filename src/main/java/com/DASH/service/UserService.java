package com.DASH.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.DASH.model.User;
import com.DASH.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
