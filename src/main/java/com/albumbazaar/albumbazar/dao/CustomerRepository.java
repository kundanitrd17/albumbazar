package com.albumbazaar.albumbazar.dao;

import java.util.List;

import com.albumbazaar.albumbazar.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByDiscountGreaterThan(Float discount);

    List<Customer> findByActive(Boolean active);

    @Query(value = "SELECT wallet FROM customer WHERE id = ?1", nativeQuery = true)
    Float getWalletAmount(Long id);

    @Query(value = "SELECT discount FROM customer WHERE id = ?1", nativeQuery = true)
    Float getDiscount(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE customer SET active = ?2 WHERE id = ?1", nativeQuery = true)
    void setActiveStatusForCustomer(Long id, boolean status);
}
