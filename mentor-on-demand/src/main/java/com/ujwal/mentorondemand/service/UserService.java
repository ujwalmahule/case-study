package com.ujwal.mentorondemand.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ujwal.mentorondemand.model.User;

public interface UserService extends UserDetailsService{

	User findOne(String userName);

}
