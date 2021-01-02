package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
import com.albumbazaar.albumbazar.form.order.OrderDetailFormDTO;
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
     * Create a new order
     * 
     * @param orderDetailFormDTO containing information about the order
     * @return an object of OrderDetail if everything goes fine orElse thrown
     *         Runtime exception
     */
    public OrderDetail createNewOrder(final OrderDetailFormDTO orderDetailFormDTO, final Long customerId);

    /**
     * @param OrderDetailForm which contains all the data regarding the order
     * @return boolean value denoting whether the tast has been completed or not
     */
    OrderDetail addOrder(final OrderDetailForm orderDetails);

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
     * @deprecated
     * @see getOrdersWithStatus instead
     */
    List<OrderDetail> getAllOrderWithStatus(String status);

    /**
     * 
     * @return all order details based on payment status
     */
    List<OrderDetail> getOrderByPaymentStatus(Boolean paymentStatus);

    /**
     * Update non null values of orderDetailDTO
     * 
     * @param orderDetailInfo
     * @return orderDetail gets returned
     */
    OrderDetail updateOrderInfo(Long id, OrderDetailDTO orderDetailInfo);

    /**
     * Change the order status
     * 
     * @param orderId        id of the order
     * @param customerCareId customer care id who is changing the order status
     * @param orderStatus    order status which will be set
     */
    void changeOrderStatus(Long orderId, Long customerCareId, OrderDetailStatus orderStatus);

    /**
     * All the orders details that are associated with a particular employee
     * 
     * @return
     */
    List<OrderDetail> getOrdersAssociatedWithEmployeeAndStatus(Long employeeId, OrderDetailStatus orderDetailStatus);

    /**
     * All available order status
     * 
     * @return A List of OrderDetailStatus enum
     */
    List<OrderDetailStatus> availableOrderStatus();

    /**
     * Return orderdetail of the order with razorpayorder id of something
     * 
     * @return
     */
    OrderDetail getOrderWithRazorpayOrderId(String razorpayOrderId);

    /**
     * Retreive a list of all the orders of a particular customer
     * 
     * @param customerId id of the customer
     * @return list of ordeDetail entity
     */
    List<OrderDetail> getOrdersOfCustomer(Long customerId);

}
