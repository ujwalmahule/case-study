package com.ujwal.mentorondemand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.CourseTemplate;


@Repository
public interface CourseTemplateRepository extends JpaRepository<CourseTemplate, Long> {

}
