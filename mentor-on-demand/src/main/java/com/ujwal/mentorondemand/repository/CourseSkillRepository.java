package com.ujwal.mentorondemand.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.id.CourseSkillId;
import com.ujwal.mentorondemand.model.CourseSkill;


@Repository
public interface CourseSkillRepository extends JpaRepository<CourseSkill, CourseSkillId> {

	List<CourseSkill> findByCourseId(Long id);

	List<CourseSkill> findBySkillId(Long id);

	List<CourseSkill> findBySkillIdAndCourseStatusId(Long id, int statusId);

}
