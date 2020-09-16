package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.customer.BasicCustomerDetailForm;
import com.albumbazaar.albumbazar.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(final CustomerService customerService) {

        this.customerService = customerService;
    }

    @GetMapping(value = "/register")
    public String viewRegisterCustomer() {

        return "add-customer";
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public String registerCustomer(@ModelAttribute BasicCustomerDetailForm customerDetail,
                                   @ModelAttribute LocationForm addressDetail) {

        System.out.println(customerDetail);
        System.out.println(addressDetail);

        customerService.registerCustomer(customerDetail, addressDetail);

        return "added";
    }

}
