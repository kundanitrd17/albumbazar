package com.albumbazaar.albumbazar.dao;

import java.util.List;

import com.albumbazaar.albumbazar.model.Employee;
import com.albumbazaar.albumbazar.model.OrderAndCustomerCareEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderAndCustomerCareRepository extends JpaRepository<OrderAndCustomerCareEntity, Long> {

    @Query(value = "SELECT * FROM  order_associated_customer_care WHERE customer_care_employee_id = ?1", nativeQuery = true)
    List<OrderAndCustomerCareEntity> findAllWithEmployeeId(Long id);

}
