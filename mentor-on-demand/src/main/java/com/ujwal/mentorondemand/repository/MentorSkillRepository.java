package com.ujwal.mentorondemand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.MentorSkill;


@Repository
public interface MentorSkillRepository extends JpaRepository<MentorSkill, Long> {

}
