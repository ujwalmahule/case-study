package com.ujwal.mentorondemand.controller;

import static com.ujwal.mentorondemand.config.Constants.TOKEN_PREFIX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ujwal.mentorondemand.config.JwtTokenUtil;
import com.ujwal.mentorondemand.model.ApiResponse;
import com.ujwal.mentorondemand.model.AuthToken;
import com.ujwal.mentorondemand.model.LoginUser;
import com.ujwal.mentorondemand.model.User;
import com.ujwal.mentorondemand.service.UserService;

@RestController
@CrossOrigin
public class JwtAuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST )
	public ApiResponse<AuthToken> authenticate(@RequestBody LoginUser loginUser) throws AuthenticationException {
		
		final String userName = loginUser.getUserName(); 
        authenticate(userName, loginUser.getPassword());
        final User user = userService.findOne(userName);
        final String token = jwtTokenUtil.generateToken(user);
        return new ApiResponse<>(200, "success",new AuthToken(userName, TOKEN_PREFIX + token));
    }

	private void authenticate(String userName, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		
	}
}
