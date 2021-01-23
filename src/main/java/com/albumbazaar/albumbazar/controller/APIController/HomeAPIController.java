package com.albumbazaar.albumbazar.controller.APIController;

import com.albumbazaar.albumbazar.model.AddressEntity;
import com.albumbazaar.albumbazar.services.AddressService;
import com.albumbazaar.albumbazar.services.UtilityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeAPIController {

    private final Logger logger = LoggerFactory.getLogger(HomeAPIController.class);

    private final AddressService addressService;
    private final UtilityService utilityService;

    @Autowired
    protected HomeAPIController(@Qualifier("utilityService") final UtilityService utilityService,
            @Qualifier("addressService") final AddressService addressService) {
        this.addressService = addressService;
        this.utilityService = utilityService;
    }

    @GetMapping(value = "/secured/address/{address-id}")
    public ResponseEntity<?> getAddress(@PathVariable("address-id") final Long addressId) {

        try {

            final AddressEntity addressEntity = addressService.getAddress(addressId);

            return ResponseEntity.ok().body(addressEntity);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();

    }

    @PostMapping(value = "/reset-code/generate/customer")
    public ResponseEntity<?> generateResetCodeForCustomer(@RequestBody final String email) {

        try {

            // System.out.println(email);
            utilityService.sendOTPToCustomer(email);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();

    }

    @PostMapping(value = "/reset-code/generate/employee")
    public ResponseEntity<?> generateResetCodeForEmployee(@RequestBody final String email) {

        try {

            utilityService.sendOTPToEmployee(email);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();

    }

}
