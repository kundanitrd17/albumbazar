package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;

public interface OrderService {

    /**
     * 
     * @param status of type OrderDetailStatus
     * @return A list of order details
     */
    List<OrderDetail> getOrdersWithStatus(OrderDetailStatus status);

    /**
     * @param OrderDetailForm which contains all the data regarding the order
     * @return boolean value denoting whether the tast has been completed or not
     */
    boolean addOrder(final OrderDetailForm orderDetails);

    /**
     * @param id of the order
     * @return OrderDetail model form
     */
    OrderDetail getOrder(final Long id);

    /**
     * 
     * @return List of OrderDetail
     */
    List<OrderDetail> getAllOrder();

    /**
     * @param the order status that needs to be fetched
     * @return a list of orders with the provided order detail
     */
    List<OrderDetail> getAllOrderWithStatus(String status);

    /**
     * 
     * @return all order details based on payment status
     */
    List<OrderDetail> getOrderByPaymentStatus(Boolean paymentStatus);

}
