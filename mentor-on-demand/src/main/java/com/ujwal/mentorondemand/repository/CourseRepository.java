package com.ujwal.mentorondemand.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.Course;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	List<Course> findByDetailsId(long id);
	Page<Course> findByDetailsId(long id, Pageable pageable);
	
	List<Course> findByStatusDesc(String desc);
	Page<Course> findByStatusDesc(String desc, Pageable pageable);
	
	Page<Course> findByDetailsNameContainingIgnoreCase(String search, PageRequest of);
	Page<Course> findByStatusIdAndDetailsNameContainingIgnoreCase(int statusId, String search, PageRequest of);
	Page<Course> findByDetailsStatusIn(int[] templateStatusIds, PageRequest of);
	Page<Course> findByDetailsNameContainingIgnoreCaseAndDetailsStatusIn(String search, int[] templateStatusIds, PageRequest of);
	
}
