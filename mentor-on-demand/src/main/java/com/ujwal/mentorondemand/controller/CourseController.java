package com.ujwal.mentorondemand.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ujwal.mentorondemand.exception.ResourceNotFoundException;
import com.ujwal.mentorondemand.model.Course;
import com.ujwal.mentorondemand.model.CourseSkill;
import com.ujwal.mentorondemand.model.CourseTemplate;
import com.ujwal.mentorondemand.model.MentorCalendar;
import com.ujwal.mentorondemand.model.MentorSkill;
import com.ujwal.mentorondemand.model.Skill;
import com.ujwal.mentorondemand.repository.CourseRepository;
import com.ujwal.mentorondemand.repository.CourseSkillRepository;
import com.ujwal.mentorondemand.repository.CourseTemplateRepository;
import com.ujwal.mentorondemand.repository.MentorCalendarRepository;
import com.ujwal.mentorondemand.repository.MentorSkillRepository;
import com.ujwal.mentorondemand.repository.SkillRepository;

@RestController
@RequestMapping("/api")
public class CourseController {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired 
	private SkillRepository skillRepository;
	
	@Autowired
	private CourseSkillRepository courseSkillRepository;
	
	@Autowired
	private MentorSkillRepository mentorSkillRepository;
	
	@Autowired
	private CourseTemplateRepository courseTemplateRepository;
	
	@Autowired
	private MentorCalendarRepository mentorCalendarRepository;
	
	@GetMapping("/courses")
	public List<Course> findAllCourses() {
		return courseRepository.findAll();
	}
	
	@GetMapping("/courses/{page}/{size}")
	public Page<Course> findAllCourses(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return courseRepository.findAll(PageRequest.of(page, size));
	}
	
	@GetMapping("/courses/{page}/{size}/{templateStatusIds}")
	public Page<Course> findAllCourses(@PathVariable(value = "templateStatusIds") int[] templateStatusIds, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return courseRepository.findByDetailsStatusIn(templateStatusIds, PageRequest.of(page, size));
	}
	
	@GetMapping("/course/{id}/calendar/{page}/{size}")
	public Page<MentorCalendar> getCalendarByMentorId(@PathVariable(value = "id") Long id, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return mentorCalendarRepository.findByCourseId(id, PageRequest.of(page, size));
	}
	
	@GetMapping("/search/courses/{search}/{page}/{size}")
	public Page<Course> searchCourses(@PathVariable(value = "search") String search, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return courseRepository.findByDetailsNameContainingIgnoreCase(search, PageRequest.of(page, size));
	}
	
	@GetMapping("/search/courses/{search}/{page}/{size}/{templateStatusIds}")
	public Page<Course> searchCourses(@PathVariable(value = "search") String search, @PathVariable(value = "templateStatusIds") int[] templateStatusIds, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return courseRepository.findByDetailsNameContainingIgnoreCaseAndDetailsStatusIn(search, templateStatusIds, PageRequest.of(page, size));
	}
	
	@GetMapping("/search/courses/{status}/{search}/{page}/{size}")
	public Page<Course> searchCourses(@PathVariable(value = "search") int statusId, @PathVariable(value = "search") String search, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return courseRepository.findByStatusIdAndDetailsNameContainingIgnoreCase(statusId, search, PageRequest.of(page, size));
	}
	
	@GetMapping("/course/{id}/skills")
	public List<CourseSkill> findCourseSkills(@PathVariable(value = "id") Long id) {
		return courseSkillRepository.findByCourseId(id);
	}
	
	@GetMapping("/skills")
	public List<Skill> findAllSkills() {
		return skillRepository.findAll();
	}
	
	@GetMapping("/skills/{page}/{size}")
	public Page<Skill> findAllSkills(@PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return skillRepository.findAll(PageRequest.of(page, size));
	}
	
	@GetMapping("/search/skills/{search}/{page}/{size}")
	public Page<Skill> searchSkills(@PathVariable(value = "search") String search, @PathVariable(value = "page") int page, @PathVariable(value = "size") int size) {
		return skillRepository.findByNameContainingIgnoreCase(search, PageRequest.of(page, size));
	}
	
	@GetMapping("/skills/{id}/courses")
	public List<CourseSkill> findSkillCourses(@PathVariable(value = "id") Long id) {
		return courseSkillRepository.findBySkillId(id);
	}
	
	@GetMapping("/skills/{id}/courses/{status}")
	public List<CourseSkill> findSkillCourses(@PathVariable(value = "id") Long id, @PathVariable(value = "status") int statusId) {
		return courseSkillRepository.findBySkillIdAndCourseStatusId(id, statusId);
	}
	
	@PostMapping("/skills")
	public Skill createSkill(@Valid @RequestBody Skill skill) {
		return skillRepository.save(skill);
	}
	
	@PostMapping("/courses")
	public Course createCourse(@Valid @RequestBody Course course) {
		return courseRepository.save(course);
	}
	
	@PostMapping("/course/template")
	public CourseTemplate createCourseTemplate(@Valid @RequestBody CourseTemplate courseTemplate) {
		return courseTemplateRepository.save(courseTemplate);
	}
	
	@PostMapping("/course/skill")
	public CourseSkill createCourse(@Valid @RequestBody CourseSkill courseSkill) {
		return courseSkillRepository.save(courseSkill);
	}
	
	@PostMapping("/calendar")
	public MentorCalendar createUser(@Valid @RequestBody MentorCalendar mentorCalendar) {
		return mentorCalendarRepository.save(mentorCalendar);
	}
	
	@PostMapping("/mentor/skill")
	public MentorSkill createCourse(@Valid @RequestBody MentorSkill mentorSkill) {
		return mentorSkillRepository.save(mentorSkill);
	}
	
	@PutMapping("/course/template/{id}")
	public CourseTemplate updateCourseTemplate(@PathVariable Long id, @Valid @RequestBody CourseTemplate template) {
		return courseTemplateRepository.findById(id).map(foundTemplate -> {
			updateTemplate(foundTemplate, template);
			return courseTemplateRepository.save(foundTemplate);
		}).orElseThrow(() -> new ResourceNotFoundException("CourseTemplate", "id", id));
	}
	
	@PutMapping("/course/{id}")
	public Course updateCourse(@PathVariable Long id, @Valid @RequestBody Course course) {
		return courseRepository.findById(id).map(foundCourse -> {
			updateCourse(foundCourse, course);
			return courseRepository.save(foundCourse);
		}).orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
	}

	private void updateCourse(Course foundCourse, @Valid Course course) {
		foundCourse.setBatchName(course.getBatchName());
		foundCourse.setStartDate(course.getStartDate());
		foundCourse.setStatus(course.getStatus());
	}

	private void updateTemplate(CourseTemplate foundTemplate, @Valid CourseTemplate template) {
		foundTemplate.setComission(template.getComission());
		foundTemplate.setDuration(template.getDuration());
		foundTemplate.setFee(template.getFee());
		foundTemplate.setName(template.getName());
		foundTemplate.setStatus(template.getStatus());
		foundTemplate.setToc(template.getToc());
	}
}
