package com.ujwal.mentorondemand.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ujwal.mentorondemand.exception.ResourceNotFoundException;
import com.ujwal.mentorondemand.model.User;
import com.ujwal.mentorondemand.model.UserRole;
import com.ujwal.mentorondemand.repository.UserRepository;
import com.ujwal.mentorondemand.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isActive(), true, true, true, getRoles(user)); 
	}

	@Override
	public User findOne(String userName) {
		return userRepository.findByEmail(userName).orElseThrow(() -> new ResourceNotFoundException("User", "email", userName));
	}

	private List<GrantedAuthority> getRoles(User user) {
    	UserRole role = user.getUserRole();
    	List<GrantedAuthority> roles;
		if(role == null) {
    		roles = new ArrayList<GrantedAuthority>();
    	} else { 
			roles = Arrays.asList(role == null ? null : new SimpleGrantedAuthority("ROLE_" + role.getName()));
    	}
    	return roles;
	}
}
