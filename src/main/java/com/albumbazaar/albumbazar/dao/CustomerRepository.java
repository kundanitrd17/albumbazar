package com.albumbazaar.albumbazar.dao;

import java.util.List;
import java.util.Optional;

import com.albumbazaar.albumbazar.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByDiscountGreaterThan(Double discount);

    List<Customer> findByActive(Boolean active);

    @Query(value = "SELECT wallet FROM customer WHERE id = ?1", nativeQuery = true)
    Double getWalletAmount(Long id);

    @Query(value = "SELECT discount FROM customer WHERE id = ?1", nativeQuery = true)
    Double getDiscount(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE customer SET active = ?2 WHERE id = ?1", nativeQuery = true)
    void setActiveStatusForCustomer(Long id, boolean status);

    Optional<Customer> findByEmail(String email);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM customer_address WHERE customer_id = ?1 AND address_id = ?2", nativeQuery = true)
    void deleteByAddressId(Long customerId, Long addressId);

    @Query(value = "SELECT email FROM customer", nativeQuery = true)
    List<String> findAllUserName();

}
