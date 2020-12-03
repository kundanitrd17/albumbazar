package com.albumbazaar.albumbazar.dao;

import java.util.List;

import com.albumbazaar.albumbazar.model.Expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(value = "SELECT * FROM expense WHERE date_time > ?1", nativeQuery = true)
    List<Expense> findAllWhereDateTimeGreaterThan(String date);

}
