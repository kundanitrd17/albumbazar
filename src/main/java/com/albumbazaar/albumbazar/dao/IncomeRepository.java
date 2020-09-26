package com.albumbazaar.albumbazar.dao;

import java.util.List;

import com.albumbazaar.albumbazar.model.Income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query(value = "SELECT * FROM income WHERE received_time > ?1", nativeQuery = true)
    List<Income> findAllWhereDateTimeGreaterThan(String date);

}
