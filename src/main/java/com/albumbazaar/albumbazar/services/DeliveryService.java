package com.albumbazaar.albumbazar.services;

import java.util.List;

import com.albumbazaar.albumbazar.dto.DeliveryOrderDTO;

public interface DeliveryService {

    /**
     * Get a list of all the orders that are yet undelivered
     * 
     * @return a list of DeliveryOrderDTO
     */

    List<DeliveryOrderDTO> undeliveredOrders();

}