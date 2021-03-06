package com.ujwal.mentorondemand.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.Mentor;


@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
	
	Optional<Mentor> findByUserProfileEmail(String email);

}
