package com.ujwal.mentorondemand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.CourseSkill;


@Repository
public interface CourseSkillRepository extends JpaRepository<CourseSkill, Long> {

}
