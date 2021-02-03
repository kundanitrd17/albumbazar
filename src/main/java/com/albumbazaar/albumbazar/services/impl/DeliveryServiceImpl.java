package com.albumbazaar.albumbazar.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.dto.DeliveryOrderDTO;
import com.albumbazaar.albumbazar.model.AddressEntity;
import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.services.DeliveryService;
import com.albumbazaar.albumbazar.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("deliveryService")
public class DeliveryServiceImpl implements DeliveryService {

    private Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    private final OrderService orderService;

    @Autowired
    protected DeliveryServiceImpl(@Qualifier("orderService") final OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeliveryOrderDTO> recentlyReceivedOrUnseenDeliveries() {

        final List<OrderDetail> orderDetails = orderService
                .getOrdersWithStatus(OrderDetailStatus.SENT_TO_DELIVERY_PARTNER);

        return orderDetails.stream().map(eachOrder -> {
            final AddressEntity address = eachOrder.getDeliveryAddress();
            final Association association = eachOrder.getAssociation();

            try {
                return DeliveryOrderDTO.builder().order_id(eachOrder.getId()).UUID_CODE("UUID_CODE")
                        .name(address.getName()).contact(address.getContactNo())
                        .pickup_address(association.getAddress().getId()).delivery_address(address.getId()).build();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            return null;
        }).filter(delivery -> delivery != null).collect(Collectors.toList());

    }

    @Override
    public List<DeliveryOrderDTO> undeliveredOrders() {

        final List<OrderDetail> allOrdersToDeliver = orderService
                .getOrdersWithStatus(OrderDetailStatus.DELIVERY_UNDER_PROCESS);

        return allOrdersToDeliver.stream().map(eachOrder -> {
            final AddressEntity address = eachOrder.getDeliveryAddress();
            final Association association = eachOrder.getAssociation();

            try {
                return DeliveryOrderDTO.builder().order_id(eachOrder.getId()).UUID_CODE("UUID_CODE")
                        .name(address.getName()).contact(address.getContactNo())
                        .pickup_address(association.getAddress().getId()).delivery_address(address.getId()).build();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            return null;
        }).filter(delivery -> delivery != null).collect(Collectors.toList());

    }

    @Override
    public List<DeliveryOrderDTO> completedDeliveries() {

        final List<OrderDetailStatus> allStatus = Arrays.asList(OrderDetailStatus.COMPLETED);

        final List<OrderDetail> allOrdersToDeliver = orderService.getOrdersWithStatus(allStatus);

        return allOrdersToDeliver.stream().map(eachOrder -> {
            final AddressEntity address = eachOrder.getDeliveryAddress();
            final Association association = eachOrder.getAssociation();

            try {
                return DeliveryOrderDTO.builder().order_id(eachOrder.getId()).UUID_CODE("UUID_CODE")
                        .name(address.getName()).contact(address.getContactNo())
                        .pickup_address(association.getAddress().getId()).delivery_address(address.getId()).build();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            return null;
        }).filter(delivery -> delivery != null).collect(Collectors.toList());

    }

}
