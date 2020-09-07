package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
