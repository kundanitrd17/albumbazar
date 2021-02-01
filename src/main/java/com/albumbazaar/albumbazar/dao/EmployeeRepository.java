package com.albumbazaar.albumbazar.dao;

import java.util.Collection;
import java.util.List;

import com.albumbazaar.albumbazar.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmail(String email);

    List<Employee> findAllByActiveAndRoleIn(boolean active, Collection<String> roles);

}
