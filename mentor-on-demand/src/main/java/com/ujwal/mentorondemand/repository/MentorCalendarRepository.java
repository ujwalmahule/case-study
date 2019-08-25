package com.ujwal.mentorondemand.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.MentorCalendar;


@Repository
public interface MentorCalendarRepository extends JpaRepository<MentorCalendar, Long> {

	Page<MentorCalendar> findByMentorId(Long id, Pageable pageable);
	
	Page<MentorCalendar> findByCourseId(Long id, Pageable pageable);
	
}
