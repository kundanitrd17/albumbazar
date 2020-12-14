package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.services.DeliveryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/delivery")
public final class DeliveryController {

    private final DeliveryService deliveryService;

    @Autowired
    DeliveryController(@Qualifier("deliveryService") final DeliveryService deliveryService) {

        this.deliveryService = deliveryService;
    }

    @GetMapping(value = { "/undelivered" })
    public @ResponseBody ResponseEntity<?> getAllOrdersToDeliver() {

        return ResponseEntity.ok().body(deliveryService.undeliveredOrders());
    }

}
