package com.ujwal.mentorondemand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ujwal.mentorondemand.model.UserRole;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
