package com.albumbazaar.albumbazar.dao;

import java.util.Collection;
import java.util.List;

import com.albumbazaar.albumbazar.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByPaymentStatus(boolean paymentStatus);

    List<OrderDetail> findByOrderStatus(String orderStatus);

    List<OrderDetail> findByOrderStatusNotIn(Collection<String> orderStatus);
}
