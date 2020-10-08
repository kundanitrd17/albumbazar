package com.albumbazaar.albumbazar.dao;

import java.util.List;

import com.albumbazaar.albumbazar.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByDiscountGreaterThan(Float discount);

    List<Customer> findByActive(Boolean active);
}
