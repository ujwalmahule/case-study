package com.ujwal.mentorondemand.controller;

import static com.ujwal.mentorondemand.constants.USER_ROLE.ADMIN;
import static com.ujwal.mentorondemand.constants.USER_ROLE.MENTOR;
import static com.ujwal.mentorondemand.constants.USER_ROLE.STUDENT;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ujwal.mentorondemand.exception.ResourceNotFoundException;
import com.ujwal.mentorondemand.exception.UserError;
import com.ujwal.mentorondemand.model.Mentor;
import com.ujwal.mentorondemand.model.MentorCalendar;
import com.ujwal.mentorondemand.model.MentorCourse;
import com.ujwal.mentorondemand.model.MentorSkill;
import com.ujwal.mentorondemand.model.Payment;
import com.ujwal.mentorondemand.model.StudentCourse;
import com.ujwal.mentorondemand.model.User;
import com.ujwal.mentorondemand.repository.MentorCalendarRepository;
import com.ujwal.mentorondemand.repository.MentorCourseRepository;
import com.ujwal.mentorondemand.repository.MentorRepository;
import com.ujwal.mentorondemand.repository.MentorSkillRepository;
import com.ujwal.mentorondemand.repository.PaymentRepository;
import com.ujwal.mentorondemand.repository.StudentCourseRepository;
import com.ujwal.mentorondemand.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MentorRepository mentorRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private StudentCourseRepository studentCourseRepository;
	
	@Autowired
	private MentorCourseRepository mentorCourseRepository;
	
	@Autowired 
	private MentorSkillRepository mentorSkillRepository;
	
	@Autowired
	private MentorCalendarRepository mentorCalendarRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RolesAllowed(ADMIN)
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@RolesAllowed(ADMIN)
	@GetMapping("/users/{page}/{size}")
	public Page<User> getAllUsers(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return userRepository.findAll(PageRequest.of(page, size));
	}
	
	@RolesAllowed({ADMIN, MENTOR, STUDENT})
	@GetMapping("/mentors")
	public List<Mentor> getAllMentorss() {
		return mentorRepository.findAll();
	}
	
	@RolesAllowed({ADMIN, MENTOR, STUDENT})
	@GetMapping("/mentors/{page}/{size}")
	public Page<Mentor> getAllMentorss(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return mentorRepository.findAll(PageRequest.of(page, size));
	}
	
	@RolesAllowed(ADMIN)
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@RolesAllowed(ADMIN)
	@PostMapping("/signup")
	public User signup(@Valid @RequestBody User user) {
		userRepository.findByEmail(user.getEmail()).ifPresent(foundUser -> {throw new UserError("User", "User already exists");});
		//by default the user is active, with no roles
		user.setActive(true);
		user.setUserRole(null);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	@RolesAllowed(ADMIN)
	@PostMapping("/mentors")
	public Mentor createUser(@Valid @RequestBody Mentor mentor) {
		mentor.getUserProfile().setPassword(bCryptPasswordEncoder.encode(mentor.getUserProfile().getPassword()));
		return mentorRepository.save(mentor);
	}
	
	@PostAuthorize("hasRole('"+ADMIN+"') OR returnObject.email == authentication.principal.username")
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable(value = "id") Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}
	
	@GetMapping("/users/{id}/payments/{page}/{size}")
	public Page<Payment> getPaymentByUserId(@PathVariable(value = "id") Long id, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return paymentRepository.findByUserId(id, PageRequest.of(page, size, Sort.by("paymentDate").descending()));
	}
	
	@GetMapping("/users/{id}/courses")
	public List<StudentCourse> getCoursesByUserId(@PathVariable(value = "id") Long id) {
		return studentCourseRepository.findByStudentId(id);
	}
	
	@GetMapping("/users/{id}/courses/{page}/{size}")
	public Page<StudentCourse> getCoursesByUserId(@PathVariable(value = "id") Long id, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return studentCourseRepository.findByStudentId(id, PageRequest.of(page, size, Sort.by("paymentDate").descending()));
	}
	
	@GetMapping("/mentors/{id}")
	public Mentor getMentorById(@PathVariable(value = "id") Long id) {
		return mentorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mentor", "id", id));
	}
	
	@GetMapping("/mentors/{id}/skills")
	public List<MentorSkill> getSkillsByMentorId(@PathVariable(value = "id") Long id) {
		return mentorSkillRepository.findByMentorId(id);
	}
	
	@GetMapping("/mentors/{id}/skills/{page}/{size}")
	public Page<MentorSkill> getSkillsByMentorId(@PathVariable(value = "id") Long id, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return mentorSkillRepository.findByMentorId(id, PageRequest.of(page, size));
	}
	
	@GetMapping("/mentors/{id}/courses")
	public List<MentorCourse> getCoursesByMentorId(@PathVariable(value = "id") Long id) {
		return mentorCourseRepository.findByMentorId(id);
	}
	
	@GetMapping("/mentors/{id}/courses/{page}/{size}")
	public Page<MentorCourse> getCoursesByMentorId(@PathVariable(value = "id") Long id, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return mentorCourseRepository.findByMentorId(id, PageRequest.of(page, size, Sort.by("paymentDate").descending()));
	}
	
	@GetMapping("/mentors/{id}/calendar/{page}/{size}")
	public Page<MentorCalendar> getCalendarByMentorId(@PathVariable(value = "id") Long id, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return mentorCalendarRepository.findByMentorId(id, PageRequest.of(page, size));
	}
	
	@GetMapping("/user/email/{email}")
	public User getUserByEmail(@PathVariable(value = "email") String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
	}
	
	@GetMapping("/mentor/email/{email}")
	public Mentor getMentorByEmail(@PathVariable(value = "email") String email) {
		return mentorRepository.findByUserProfileEmail(email).orElseThrow(() -> new ResourceNotFoundException("Mentor", "email", email));
	}
	
	@GetMapping("user/role/{roleName}")
	public List<User> getUserByRole(@PathVariable(value = "roleName") String roleName) {
		return userRepository.findByUserRoleName(roleName);
	}
	
	@GetMapping("user/role/{roleName}/{page}/{size}")
	public Page<User> getUserByRole(@PathVariable(value = "roleName") String roleName, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return userRepository.findByUserRoleName(roleName, PageRequest.of(page, size));
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
		return userRepository.findById(id).map(foundUser -> {
			updateUser(foundUser, user);
			return userRepository.save(foundUser);
		}).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}
	
	@PutMapping("/mentor/{id}")
	public Mentor updateMentor(@PathVariable Long id, @Valid @RequestBody Mentor mentor) {
		return mentorRepository.findById(id).map(foundMentor -> {
			updateMentor(foundMentor, mentor);
			return mentorRepository.save(foundMentor);
		}).orElseThrow(() -> new ResourceNotFoundException("Mentor", "id", id));
	}
	
	@PutMapping("/user/email/{email}")
	public User updateUser(@PathVariable String email, @Valid @RequestBody User user) {
		return userRepository.findByEmail(email).map(foundUser -> {
			updateUser(foundUser, user);
			return userRepository.save(foundUser);
		}).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
	}

	@PutMapping("/mentor/email/{email}")
	public Mentor updateMentor(@PathVariable String email, @Valid @RequestBody Mentor mentor) {
		return mentorRepository.findByUserProfileEmail(email).map(foundMentor -> {
			updateMentor(foundMentor, mentor);
			return mentorRepository.save(foundMentor);
		}).orElseThrow(() -> new ResourceNotFoundException("Mentor", "email", email));
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		return userRepository.findById(id).map(foundUser -> {
			userRepository.delete(foundUser);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}
	
	@DeleteMapping("/mentor/{id}")
	public ResponseEntity<?> deleteMentor(@PathVariable Long id) {
		return mentorRepository.findById(id).map(foundMentor -> {
			mentorRepository.delete(foundMentor);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Mentor", "id", id));
	}
	
	@DeleteMapping("/user/email/{email}")
	public ResponseEntity<?> deleteUser(@PathVariable String email) {
		return userRepository.findByEmail(email).map(foundUser -> {
			userRepository.delete(foundUser);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
	}
	
	@DeleteMapping("/mentor/email/{email}")
	public ResponseEntity<?> deleteMentor(@PathVariable String email) {
		return mentorRepository.findByUserProfileEmail(email).map(foundMentor -> {
			mentorRepository.delete(foundMentor);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Mentor", "email", email));
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
		if(user.getPassword()!=null) {
			foundUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		foundUser.setPin(user.getPin());
		foundUser.setState(user.getState());
		foundUser.setUserRole(user.getUserRole());
	}
	
	private void updateMentor(Mentor foundMentor, @Valid Mentor mentor) {
		foundMentor.setExperience(mentor.getExperience());
		foundMentor.setLinkedIn(mentor.getLinkedIn());
		foundMentor.setSummary(mentor.getSummary());
		foundMentor.setVerified(mentor.isVerified());
		updateUser(foundMentor.getUserProfile(), mentor.getUserProfile());
	}
}
