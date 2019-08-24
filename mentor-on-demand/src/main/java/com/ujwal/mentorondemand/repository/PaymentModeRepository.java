package com.ujwal.mentorondemand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.PaymentMode;


@Repository
public interface PaymentModeRepository extends JpaRepository<PaymentMode, Long> {

}
