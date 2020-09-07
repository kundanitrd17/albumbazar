package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
}
