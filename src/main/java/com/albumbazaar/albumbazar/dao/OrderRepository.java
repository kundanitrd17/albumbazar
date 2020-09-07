package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetail, Long> {
}
