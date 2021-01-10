package com.albumbazaar.albumbazar.controller.APIController;

import java.util.NoSuchElementException;

import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.services.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secured/employee")
public class EmployeeRequiredAPI {

    private final Logger logger = LoggerFactory.getLogger(EmployeeRequiredAPI.class);

    private final CustomerService customerService;

    protected EmployeeRequiredAPI(@Qualifier("customerService") final CustomerService customerService) {
        this.customerService = customerService;

    }

    @GetMapping(value = "/customer/info/{customer-id}")
    public ResponseEntity<?> customerDetails(@PathVariable("customer-id") final Long customerId) {

        try {
            final Customer customer = customerService.getCustomer(customerId);
            final CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setName(customer.getName());
            customerDTO.setEmail(customer.getEmail());
            customerDTO.setContactNo(customer.getContactNo());

            return ResponseEntity.ok().body(customerDTO);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

}
