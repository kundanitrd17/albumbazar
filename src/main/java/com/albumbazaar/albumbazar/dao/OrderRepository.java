package com.albumbazaar.albumbazar.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.model.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByPaymentStatus(boolean paymentStatus);

    List<OrderDetail> findByOrderStatus(String orderStatus);

    List<OrderDetail> findByOrderStatusNotIn(Collection<String> orderStatus);

    @Query(value = "SELECT * FROM order_detail WHERE employee_id = ?1 and order_status = ?2", nativeQuery = true)
    List<OrderDetail> findAllByEmployeeId(final Long employeeId, final String status);

    @Query(value = "SELECT * FROM order_detail WHERE customer_id = ?1", nativeQuery = true)
    Optional<List<OrderDetail>> findAllByCustomerId(Long customerId);

    Optional<OrderDetail> findByRazorpayOrderId(String razorpayOrderId);

    Optional<OrderDetail> findByRazorpayPaymentId(String razorpayPaymentId);

    @Query(value = "SELECT * FROM order_detail WHERE is_forwarded_to_association = true AND association_id = ?1 AND has_association_accepted = ?2", nativeQuery = true)
    List<OrderDetail> findAllByAssociationIdAndHasAssociationAccepted(Long associationId, boolean status);

    List<OrderDetail> findAllByAssociationIdAndOrderStatus(Long associationId, String orderStatus);

    @Query(value = "SELECT * FROM order_detail WHERE association_id = ?1 AND has_association_accepted = true AND order_status not in ('READY_TO_DELIVER', 'DELIVER', 'UNDER_DELIVERY', 'DELIVERED', 'COMPLETED')", nativeQuery = true)
    List<OrderDetail> findAllUnderProcessByAssociationId(Long associationId);

    List<OrderDetail> findAllByAssociationAndOrderStatus(Association association, String orderStatus);
}
