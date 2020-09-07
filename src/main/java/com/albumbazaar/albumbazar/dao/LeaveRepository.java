package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.EmployeeLeave;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<EmployeeLeave, Long>{
    
}
