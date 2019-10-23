package com.ujwal.mentorondemand.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.MentorCourse;


@Repository
public interface MentorCourseRepository extends JpaRepository<MentorCourse, Long> {
	
	List<MentorCourse> findByMentorId(Long id);
	Page<MentorCourse> findByMentorId(Long id, Pageable pageable);
	
}
