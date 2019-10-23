package com.ujwal.mentorondemand.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	Page<Payment> findByUserId(Long id, Pageable pageable);
	
	Page<Payment> findByCourseId(Long id, Pageable pageable);
	
}
