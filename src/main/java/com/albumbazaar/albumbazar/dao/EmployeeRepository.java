package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    
    
}
