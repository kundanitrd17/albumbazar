package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.model.OrderAndCustomerCareEntity;
import com.albumbazaar.albumbazar.principals.CustomerPrincipal;
import com.albumbazaar.albumbazar.services.CustomerCareEmployeeService;
import com.albumbazaar.albumbazar.utilities.OrderAndCustomerCarePool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public final class AllOrdersReceivedPool {

    private final CustomerCareEmployeeService customerCareEmployeeService;
    private Logger logger = LoggerFactory.getLogger(AllOrdersReceivedPool.class);

    @Autowired(required = true)
    protected AllOrdersReceivedPool(
            @Qualifier("customerCareEmployeeService") final CustomerCareEmployeeService customerCareEmployeeService) {
        this.customerCareEmployeeService = customerCareEmployeeService;
    }

    @MessageMapping(value = "/customer-care/publish/order-pool")
    @SendTo(value = "/customer-care/subscribe/order-pool")
    public OrderAndCustomerCarePool orderPoolAreaResource(
            @RequestBody final OrderAndCustomerCarePool orderAndCustomerCare) {

        logger.info(orderAndCustomerCare.toString());
        /**
         * Get the Principal object from SecurityContextHolder and populate
         * orderAndCustomerCare.customerCareId
         */
        // System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        try {
            // final CustomerPrincipal principal = (CustomerPrincipal)
            // SecurityContextHolder.getContext()
            // .getAuthentication().getPrincipal();

            // orderAndCustomerCare.setCustomerCareId(principal.getId());

            final OrderAndCustomerCareEntity orderAndCustomerCareEntity = customerCareEmployeeService
                    .addOrderOfCustomerCare(orderAndCustomerCare);

            logger.info(orderAndCustomerCareEntity.toString());

            return orderAndCustomerCare;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return null;
    }

}
