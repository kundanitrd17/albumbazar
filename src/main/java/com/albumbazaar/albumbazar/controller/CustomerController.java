package com.albumbazaar.albumbazar.controller;

import java.util.List;

import javax.validation.Valid;

import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.dto.ErrorDTO;
import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.customer.BasicCustomerDetailForm;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.services.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "customer")
public final class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;

    @Autowired
    public CustomerController(final CustomerService customerService) {

        this.customerService = customerService;
    }

    @PutMapping("/info")
    @ResponseBody
    public ResponseEntity<?> emp(@RequestBody @Valid CustomerDTO customerInfo) {

        try {
            final Customer customer = customerService.updateCustomerInfo(customerInfo);

            return ResponseEntity.ok().body(customer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            final ErrorDTO error = new ErrorDTO();
            error.setMessage("Unable to update info");
            return ResponseEntity.badRequest().body(error);
        }

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

        try {
            customerService.registerCustomer(customerDetail, addressDetail);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "Not added";
        }

        return "added";
    }

    @GetMapping(value = "")
    public ModelAndView getAllCustomer() {

        final ModelAndView modelAndView = new ModelAndView("superuser/customer");

        modelAndView.addObject("customers", customerService.getAllCustomer());

        return modelAndView;
    }

    @GetMapping(value = "discounted")
    public ModelAndView getDiscountedCustomer() {
        final ModelAndView modelAndView = new ModelAndView("superuser/customer");
        modelAndView.addObject("customers", customerService.getDiscountedCustomer());

        return modelAndView;
    }

    @GetMapping(value = "blocked")
    public ModelAndView getBlockedCustomer() {
        final ModelAndView modelAndView = new ModelAndView("superuser/customer");
        modelAndView.addObject("customers", customerService.getBlockeList());

        return modelAndView;
    }

    @GetMapping(value = "my-order")
    public ModelAndView viewAllMyOrders() {

        final ModelAndView modelAndView = new ModelAndView("customer_orders");
        // Get customer principal from the Security Context principal
        final Long customerId = 1l;

        try {
            List<OrderDetailDTO> orders = customerService.getAllOrderDetails(customerId);
            System.out.println(orders);
            modelAndView.addObject("allOrdersForCustomer", orders);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return modelAndView;
    }

    @PostMapping("my-order/pay-or-upload")
    public RedirectView getPaymentOrUploadForAnOrder(@RequestParam("orderId") String orderId,
            final RedirectAttributes redirectAttributes) {
        System.out.println("Order Id: " + orderId);

        final RedirectView redirectView = new RedirectView("/order/upload-photo");

        if (orderId == null || orderId.isBlank()) {
            redirectView.setUrl("/customer/my-order");
            return redirectView;
        }

        redirectAttributes.addFlashAttribute("order_id", orderId);

        return redirectView;

    }

}
