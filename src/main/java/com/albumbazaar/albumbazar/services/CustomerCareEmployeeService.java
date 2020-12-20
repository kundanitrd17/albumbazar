package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.model.OrderAndCustomerCareEntity;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.utilities.OrderAndCustomerCarePool;

public interface CustomerCareEmployeeService {

    public OrderAndCustomerCareEntity addOrderOfCustomerCare(OrderAndCustomerCarePool orderAndCustomerCare);

    public List<OrderDetail> acceptedOrdersByCustomerCare(Long customerCareId);

}
