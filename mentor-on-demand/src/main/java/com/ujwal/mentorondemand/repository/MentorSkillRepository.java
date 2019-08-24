package com.ujwal.mentorondemand.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.MentorSkill;


@Repository
public interface MentorSkillRepository extends JpaRepository<MentorSkill, Long> {

	List<MentorSkill> findByMentorId(Long id);
	Page<MentorSkill> findByMentorId(Long id, Pageable pageable);
	
	List<MentorSkill> findBySkillId(Long id);
	Page<MentorSkill> findBySkillId(Long id, Pageable pageable);
	
}
