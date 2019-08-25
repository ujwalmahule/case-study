package com.ujwal.mentorondemand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.CourseStatus;


@Repository
public interface CourseStatusRepository extends JpaRepository<CourseStatus, Long> {

}
