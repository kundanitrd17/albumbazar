package com.albumbazaar.albumbazar.services;

import com.albumbazaar.albumbazar.model.OrderAndCustomerCareEntity;
import com.albumbazaar.albumbazar.utilities.OrderAndCustomerCarePool;

public interface CustomerCareEmployeeService {

    public OrderAndCustomerCareEntity addOrderOfCustomerCare(OrderAndCustomerCarePool orderAndCustomerCare);

}
