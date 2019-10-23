package com.ujwal.mentorondemand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.Wallet;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
	
}
