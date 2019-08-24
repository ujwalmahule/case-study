package com.ujwal.mentorondemand.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	List<Payment> findByUserId(Long id);
	Page<Payment> findByUserId(Long id, Pageable pageable);
	
	List<Payment> findByCourseId(Long id);
	Page<Payment> findByCourseId(Long id, Pageable pageable);
	
}
