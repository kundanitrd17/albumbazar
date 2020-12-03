package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.model.OrderAndCustomerCareEntity;
import com.albumbazaar.albumbazar.services.CustomerCareEmployeeService;
import com.albumbazaar.albumbazar.utilities.OrderAndCustomerCarePool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AllOrdersReceivedPool {

    private CustomerCareEmployeeService customerCareEmployeeService;

    @Autowired
    protected AllOrdersReceivedPool(
            @Qualifier("customerCareEmployeeService") CustomerCareEmployeeService customerCareEmployeeService) {
        this.customerCareEmployeeService = customerCareEmployeeService;
    }

    @MessageMapping(value = "/customer-care/publish/order-pool")
    @SendTo(value = "/customer-care/subscribe/order-pool")
    public ResponseEntity<?> orderPoolAreaResource(@RequestBody final OrderAndCustomerCarePool orderAndCustomerCare) {

        System.out.println(orderAndCustomerCare);
        try {
            final OrderAndCustomerCareEntity orderAndCustomerCareEntity = customerCareEmployeeService
                    .addOrderOfCustomerCare(orderAndCustomerCare);
            System.out.println(orderAndCustomerCareEntity);
            return ResponseEntity.ok().body(orderAndCustomerCareEntity);
        } catch (Exception e) {
            System.out.println("Something wrong: " + e.getMessage());
        }

        return ResponseEntity.badRequest().body("error");
    }

}
