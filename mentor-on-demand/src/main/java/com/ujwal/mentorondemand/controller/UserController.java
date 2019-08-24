package com.ujwal.mentorondemand.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ujwal.mentorondemand.exception.ResourceNotFoundException;
import com.ujwal.mentorondemand.model.Mentor;
import com.ujwal.mentorondemand.model.User;
import com.ujwal.mentorondemand.repository.MentorRepository;
import com.ujwal.mentorondemand.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MentorRepository mentorReporitory;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PostMapping("/mentors")
	public Mentor createUser(@Valid @RequestBody Mentor mentor) {
		return mentorReporitory.save(mentor);
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable(value = "id") Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}
	
	@GetMapping("/user/email/{email}")
	public User getUserById(@PathVariable(value = "email") String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
	}
	
	@GetMapping("user/role/{roleName}")
	public List<User> getUserByRole(@PathVariable(value = "roleName") String roleName) {
		return userRepository.findByUserRoleName(roleName);
	}
	
	@PutMapping("/user/email/{email}")
	public User updateUser(@PathVariable String email, @Valid @RequestBody User user) {
		return userRepository.findByEmail(email).map(foundUser -> {
			updateUser(foundUser, user);
			return userRepository.save(foundUser);
		}).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
		return userRepository.findById(id).map(foundUser -> {
			updateUser(foundUser, user);
			return userRepository.save(foundUser);
		}).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		return userRepository.findById(id).map(foundUser -> {
			userRepository.delete(foundUser);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}
	
	@DeleteMapping("/user/email/{email}")
	public ResponseEntity<?> deleteUser(@PathVariable String email) {
		return userRepository.findByEmail(email).map(foundUser -> {
			userRepository.delete(foundUser);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
	}
	
	private void updateUser(User foundUser, @Valid User user) {
		foundUser.setActive(user.isActive());
		foundUser.setAddressLine1(user.getAddressLine1());
		foundUser.setAddressLine2(user.getAddressLine2());
		foundUser.setCity(user.getCity());
		foundUser.setCountry(user.getCity());
		foundUser.setDob(user.getDob());
		foundUser.setEmail(user.getEmail());
		foundUser.setFirstName(user.getFirstName());
		foundUser.setLastName(user.getLastName());
		foundUser.setMobile(user.getMobile());
		foundUser.setPassword(user.getPassword());
		foundUser.setPin(user.getPin());
		foundUser.setState(user.getState());
		foundUser.setUserRole(user.getUserRole());
	}
}
