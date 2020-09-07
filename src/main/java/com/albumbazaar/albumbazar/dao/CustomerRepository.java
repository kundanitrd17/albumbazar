package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
