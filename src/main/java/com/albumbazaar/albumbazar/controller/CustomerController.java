package com.albumbazaar.albumbazar.controller;

import java.util.List;

import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.customer.BasicCustomerDetailForm;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(final CustomerService customerService) {

        this.customerService = customerService;
    }

    @GetMapping(value = "/register")
    public String viewRegisterCustomer() {

        return "/add-customer";
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

    @GetMapping(value = "")
    @ResponseBody
    public List<Customer> getAllCustomer() {

        return customerService.getAllCustomer();
    }

    @GetMapping(value = "discounted")
    @ResponseBody
    public List<Customer> getDiscountedCustomer() {
        return customerService.getDiscountedCustomer();
    }

    @GetMapping(value = "blocked")
    @ResponseBody
    public List<Customer> getBlockedCustomer() {
        return customerService.getBlockeList();
    }
}
