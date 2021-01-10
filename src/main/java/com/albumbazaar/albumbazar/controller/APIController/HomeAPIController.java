package com.albumbazaar.albumbazar.controller.APIController;

import com.albumbazaar.albumbazar.model.AddressEntity;
import com.albumbazaar.albumbazar.services.AddressService;
import com.albumbazaar.albumbazar.services.DeliveryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secured")
public class HomeAPIController {

    private final Logger logger = LoggerFactory.getLogger(HomeAPIController.class);

    private final AddressService addressService;

    @Autowired
    protected HomeAPIController(@Qualifier("addressService") final AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value = "/address/{address-id}")
    public ResponseEntity<?> getAddress(@PathVariable("address-id") final Long addressId) {

        try {

            final AddressEntity addressEntity = addressService.getAddress(addressId);

            return ResponseEntity.ok().body(addressEntity);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();

    }

}
