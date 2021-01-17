package com.albumbazaar.albumbazar.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.albumbazaar.albumbazar.Mapper.AddressMapper;
import com.albumbazaar.albumbazar.Mapper.CustomerMapper;
import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.model.AddressEntity;
import com.albumbazaar.albumbazar.model.AvailableRoles;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.principals.CustomerPrincipal;
import com.albumbazaar.albumbazar.services.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerController(final CustomerService customerService, final CustomerMapper customerMapper) {

        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @Autowired
    AddressMapper addressMapper;

    @GetMapping(value = "/customer/my-account")
    public ModelAndView myAccountProfileInfo() {
        final ModelAndView modelAndView = new ModelAndView("my_account");

        try {
            // Get Customer id from the pricipal objects
            final CustomerPrincipal principal = (CustomerPrincipal) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            final Customer customer = customerService.getCustomer(principal.getId());
            final CustomerDTO customerDTO = customerMapper.customerEntityToCustomerDTO(customer);

            modelAndView.addObject("customer", customerDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            modelAndView.addObject("error", "Unable to Authenticate you");
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping(value = "/customer/my-address")
    public ModelAndView myAccountManageAddress() {
        final ModelAndView modelAndView = new ModelAndView("manage_address");

        try {

            // Get Customer id from the pricipal objects
            final CustomerPrincipal principal = (CustomerPrincipal) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            final Customer customer = customerService.getCustomer(principal.getId());
            final Set<AddressEntity> addresses = customer.getAddress();

            modelAndView.addObject("addresses", addresses);
            final CustomerDTO customerDTO = customerMapper.customerEntityToCustomerDTO(customer);
            modelAndView.addObject("customer", customerDTO);

        } catch (Exception e) {
            logger.error(e.getMessage());
            modelAndView.setViewName("redirect:/");
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
            final Customer savedCustomer = customerService.registerCustomer(customerDTO);

            // List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(AvailableRoles.Code.USER));
            // CustomerPrincipal principal = new CustomerPrincipal(savedCustomer);
            // Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, authorities);
            // SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(savedCustomer);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return "redirect:/";
    }

    @GetMapping(value = "/customer/my-order")
    public ModelAndView viewAllMyOrders() {

        final ModelAndView modelAndView = new ModelAndView("customer_orders");

        try {
            // Get Customer id from the pricipal objects
            final CustomerPrincipal principal = (CustomerPrincipal) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            List<OrderDetail> orders = customerService.getAllOrderDetails(principal.getId());
            System.out.println(orders);

            modelAndView.addObject("allOrdersForCustomer", orders);
        } catch (Exception e) {
            logger.error(e.getMessage());
            modelAndView.addObject("error", "No Orders yet!");
        }

        return modelAndView;
    }

    @PostMapping("customer/my-order/pay-or-upload")
    public RedirectView getPaymentOrUploadForAnOrder(@RequestParam("orderId") String orderId,
            final RedirectAttributes redirectAttributes) {
        logger.info("Order Id: " + orderId);

        final RedirectView redirectView = new RedirectView("/customer/order/upload-photo");

        if (orderId == null || orderId.isBlank()) {
            redirectView.setUrl("/customer/my-order");
            redirectAttributes.addAttribute("error", "Order Not found");
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
            System.out.println(addressDTO);
            // Get Customer id from the pricipal objects
            final CustomerPrincipal principal = (CustomerPrincipal) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            customerService.updateOrAddAddress(addressDTO, principal.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
            redirectAttributes.addAttribute("error", true);
        }

        return redirectView;
    }




}
