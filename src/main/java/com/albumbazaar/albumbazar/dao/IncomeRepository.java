package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
