package com.albumbazaar.albumbazar.controller.APIController;

import com.albumbazaar.albumbazar.dto.ErrorDTO;
import com.albumbazaar.albumbazar.services.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/admin")
public class AdminControllerAPI {

    private final Logger logger = LoggerFactory.getLogger(AdminControllerAPI.class);

    private final CustomerService customerService;

    @Autowired(required = true)
    protected AdminControllerAPI(@Qualifier("customerService") final CustomerService customerService) {
        this.customerService = customerService;
    }

    @DeleteMapping(value = "customer-delete")
    public ResponseEntity<?> deactivateCustomer(@RequestBody Long customerId) {

        try {
            customerService.deleteCustomer(customerId);
            return ResponseEntity.ok().body("done deleting");
        } catch (Exception e) {
            logger.error(e.getMessage());

            final ErrorDTO error = new ErrorDTO();
            error.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }

    }

    @PutMapping(value = "customer-restore")
    public ResponseEntity<?> activateCustomer(@RequestBody Long customerId) {
        try {

            customerService.restoreCustomer(customerId);
            return ResponseEntity.ok().body("done restoring");
        } catch (Exception e) {
            logger.error(e.getMessage());
            final ErrorDTO error = new ErrorDTO();
            error.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }

    }

}
