package com.ujwal.mentorondemand.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	List<Course> findByDetailsId(long id);
	List<Course> findByDetailsId(long id, Pageable pageable);
	
	List<Course> findByStatusDesc(String desc);
	List<Course> findByStatusDesc(String desc, Pageable pageable);
	
}
