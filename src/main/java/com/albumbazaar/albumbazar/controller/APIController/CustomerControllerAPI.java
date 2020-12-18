package com.albumbazaar.albumbazar.controller.APIController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.GoogleDriveService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/secured/customer")
public class CustomerControllerAPI {

    private final Logger logger = LoggerFactory.getLogger(CustomerControllerAPI.class);

    private final CustomerService customerService;
    private final GoogleDriveService googleDriveService;

    protected CustomerControllerAPI(@Qualifier("customerService") final CustomerService customerService,
            @Qualifier("googleDriveService") final GoogleDriveService googleDriveService) {
        this.customerService = customerService;
        this.googleDriveService = googleDriveService;
    }

    @GetMapping(value = "wallet/{id}")
    public ResponseEntity<?> getWalletInfo(@PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok().body(customerService.getWalletAmount(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.notFound().build();

    }

}
