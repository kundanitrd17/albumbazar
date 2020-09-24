package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
import com.albumbazaar.albumbazar.model.OrderDetail;

public interface OrderService {

    boolean addOrder(final OrderDetailForm orderDetails);

    OrderDetail getOrder(final Long id);

    List<OrderDetail> getAllOrder();

}
