package com.albumbazaar.albumbazar.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.albumbazaar.albumbazar.Mapper.AddressMapper;
import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.services.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public final class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;

    @Autowired
    public CustomerController(final CustomerService customerService) {

        this.customerService = customerService;
    }

    @Autowired
    AddressMapper addressMapper;

    @GetMapping(value = "/my-account")
    public ModelAndView myAccountProfileInfo() {
        final ModelAndView modeAndView = new ModelAndView("my_account");

        try {
            // Get Customer id from the pricipal objects
            modeAndView.addObject("customer", customerService.getCustomer(1l));
        } catch (Exception e) {
            logger.error(e.getMessage());
            modeAndView.addObject("error", "Unable to Authenticate you");
            modeAndView.setViewName("redirect:/");
        }

        return modeAndView;
    }

    @GetMapping(value = "/customer/my-address")
    public ModelAndView myAccountManageAddress() {
        final ModelAndView modelAndView = new ModelAndView("manage_address");

        try {

            final Customer customer = customerService.getCustomer(1l);
            final Set<AddressDTO> addresses = customer.getAddress().stream().map(addressMapper::addressToAddressDto)
                    .collect(Collectors.toSet());
            modelAndView.addObject("addresses", addresses);
            modelAndView.addObject("customer", customer);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return modelAndView;
    }

    @PostMapping(value = "/register")
    public String registerCustomer(@ModelAttribute @Valid final CustomerDTO customerDTO,
            final BindingResult bindingResult) {
        // final ModelAndView modelAndView = new ModelAndView("");
        if (bindingResult.hasErrors()) {
            logger.info("Invalid Details");
            return "redirect:/";
        }

        try {
            System.out.println(customerDTO);
            System.out.println(customerDTO.getReferralCode());
            customerService.registerCustomer(customerDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return "redirect:/";
    }

    @GetMapping(value = "customer/my-order")
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

    @PostMapping("customer/my-order/pay-or-upload")
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

    @PostMapping(value = "customer/address/info")
    public RedirectView updateAddressOfCustomer(@Valid @ModelAttribute final AddressDTO addressDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/customer/my-address");
        if (bindingResult.hasErrors()) {
            logger.error("Invalid Address information");
            redirectAttributes.addAttribute("error", "Invalid Data");
            return redirectView;
        }

        try {
            // final CustomerPrincipal customerPrincipal = (CustomerPrincipal)
            // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            customerService.updateOrAddAddress(addressDTO, 1l);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return redirectView;
    }

}
