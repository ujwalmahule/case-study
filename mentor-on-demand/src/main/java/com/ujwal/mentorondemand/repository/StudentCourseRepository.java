package com.ujwal.mentorondemand.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.StudentCourse;


@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
	
	List<StudentCourse> findByStudentId(Long id);
	Page<StudentCourse> findByStudentId(Long id, Pageable pageable);
	
}
