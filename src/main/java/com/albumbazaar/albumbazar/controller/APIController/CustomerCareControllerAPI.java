package com.albumbazaar.albumbazar.controller.APIController;

import com.albumbazaar.albumbazar.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customer-care")
@Slf4j
public class CustomerCareControllerAPI {
    

    private final OrderService orderService;
    
    @Autowired
    protected CustomerCareControllerAPI(
        @Qualifier("orderService") final OrderService orderService
    ) {
        this.orderService = orderService;
    }

    
    @PutMapping("/order/{order_id}/description")
    public ResponseEntity<?> changeOrderDescriptionCustomerCare(
        @PathVariable("order_id") final Long orderId,
        @RequestBody final String description
    ) {

        try {
            orderService.changeOrderDescription(orderId, description);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }





}
