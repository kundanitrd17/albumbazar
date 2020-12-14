package com.albumbazaar.albumbazar.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.albumbazaar.albumbazar.dto.DeliveryOrderDTO;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.services.DeliveryService;
import com.albumbazaar.albumbazar.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("deliveryService")
public final class DeliveryServiceImpl implements DeliveryService {

    private final OrderService orderService;

    @Autowired
    protected DeliveryServiceImpl(@Qualifier("orderService") final OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public List<DeliveryOrderDTO> undeliveredOrders() {

        List<OrderDetail> allOrdersToDeliver = orderService.getOrdersWithStatus(OrderDetailStatus.DELIVER);

        List<DeliveryOrderDTO> allUndDeliveryOrderDTOs = new ArrayList<>(100);

        allOrdersToDeliver.stream().forEach(each_item -> {
            DeliveryOrderDTO item = new DeliveryOrderDTO();
            item.setFromAddress("fromAddress");
            item.setToAddress("to Address");
            item.setId(each_item.getId());
            item.setUUID_CODE("UUITLD");
            allUndDeliveryOrderDTOs.add(item);
        });

        return allUndDeliveryOrderDTOs;
    }

}
