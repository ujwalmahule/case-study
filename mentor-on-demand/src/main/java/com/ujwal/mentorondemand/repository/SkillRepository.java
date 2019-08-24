package com.ujwal.mentorondemand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.Skill;


@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

}
