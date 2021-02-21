package com.albumbazaar.albumbazar.controller;

import java.util.List;

import javax.validation.Valid;

import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.principals.EmployeePrincipal;
import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.CustomerCareEmployeeService;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.EmployeeService;
import com.albumbazaar.albumbazar.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "/admin")
public final class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final OrderService orderService;
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final AssociationService associationService;

    @Autowired
    public AdminController(@Qualifier("employeeService") final EmployeeService employeeService,
            @Qualifier("customerService") final CustomerService customerService,
            @Qualifier("associationService") final AssociationService associationService,
            @Qualifier("orderService") final OrderService orderService) {
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.orderService = orderService;
        this.associationService = associationService;
    }

    @Autowired
    CustomerCareEmployeeService customerCareEmployeeService;

    @GetMapping(value = "")
    public String adminView() {
        return "admin/admin_home";
    }

    @GetMapping(value = "/login")
    public String adminLoginView() {

        return "admin/admin_login_panel";
    }

    @PostMapping(value = "/order/address/change")
    public RedirectView updateAddressOfCustomer(@Valid @ModelAttribute final AddressDTO addressDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

        final RedirectView redirectView = new RedirectView("/admin/order/accepted");
        if (bindingResult.hasErrors()) {
            logger.error("Invalid Address information");
            redirectAttributes.addAttribute("error", "Invalid Data");
            return redirectView;
        }

        try {

            System.out.println(addressDTO);

            // Get Customer id from the pricipal objects
            final EmployeePrincipal principal = (EmployeePrincipal) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            orderService.changeDeliveryAddress(addressDTO, principal.getId());

        } catch (Exception e) {
            logger.error(e.getMessage());
            redirectAttributes.addAttribute("error", true);
        }

        return redirectView;
    }

    // Orders endpoints for superuser

    @GetMapping(value = "/order-list")
    public ModelAndView adminOrderListView(@RequestParam(value = "payment", defaultValue = "") String paymentStatus,
            @RequestParam(value = "status", defaultValue = "completed") final String orderStatus) {

        System.out.println(paymentStatus.isBlank());

        final ModelAndView modelAndView = new ModelAndView("/admin/order-list");

        // If payment Option is specified than only send payment info related order
        // detail
        if (!paymentStatus.isBlank()) {
            try {
                final boolean paymentStatusBoolean = Boolean.parseBoolean(paymentStatus);
                if (paymentStatusBoolean) {
                    modelAndView.addObject("title", "Paid Orders");
                } else {
                    modelAndView.addObject("title", "Un-Paid Orders");
                }
                modelAndView.addObject("order_details", orderService.getOrderByPaymentStatus(paymentStatusBoolean));
            } catch (Exception e) {
                logger.error(e.getMessage());
                modelAndView.addObject("order_details", null);
            }

            return modelAndView;
        } else {
            // If payment status is not specified then send order details based on order
            // status
            try {
                modelAndView.addObject("title", orderStatus + " Orders");
                modelAndView.addObject("order_details", orderService.getAllOrderWithStatus(orderStatus));
            } catch (Exception e) {
                logger.error(e.getMessage());
                modelAndView.addObject("order_details", null);
            }

            return modelAndView;
        }

    }

    // EndPoints for customer controller

    @GetMapping(value = "/customer")
    public ModelAndView getAllCustomer() {

        final ModelAndView modelAndView = new ModelAndView("admin/customer_list");

        modelAndView.addObject("customers", customerService.getAllCustomer());

        return modelAndView;
    }

    @GetMapping(value = "/customer/discounted")
    public ModelAndView getDiscountedCustomer() {
        final ModelAndView modelAndView = new ModelAndView("admin/customer_list");
        modelAndView.addObject("customers", customerService.getDiscountedCustomer());

        return modelAndView;
    }

    @GetMapping(value = "/customer/blocked")
    public ModelAndView getBlockedCustomer() {
        final ModelAndView modelAndView = new ModelAndView("admin/customer_list");
        modelAndView.addObject("customers", customerService.getBlockeList());

        return modelAndView;
    }

    // Employee related endpoints
    @GetMapping(value = "/employee-list")
    public ModelAndView allEmployeeView() {
        ModelAndView modelAndView = new ModelAndView("/admin/employee_list");

        try {
            modelAndView.addObject("employees", employeeService.getAllEmployee());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return modelAndView;
    }

    @GetMapping(value = "/order/order-pool")
    public ModelAndView customerCareOrderPool(Model model) {
        final ModelAndView modelAndView = new ModelAndView("admin/order_pool_area");

        try {
            final List<OrderDetail> recentlyReceivedOrders = orderService
                    .getOrdersWithStatus(OrderDetailStatus.PENDING);

            // modelAndView.addObject("employee_id", 1);
            final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof EmployeePrincipal) {
                final EmployeePrincipal employeePrincipal = (EmployeePrincipal) principal;
                modelAndView.addObject("employee_id", employeePrincipal.getId());
            } else {
                throw new RuntimeException("UnAuthorized");

            }

            modelAndView.addObject("recentlyReceivedOrders", recentlyReceivedOrders);
            // } catch (AuthenticationException e) {
            // logger.error(e.getMessage());

            // modelAndView.setViewName("redirect:/customer-care/login");
            // SecurityContextHolder.getContext().setAuthentication(null);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return modelAndView;

    }

    @GetMapping(value = "/order/accepted")
    public ModelAndView acceptedOrdersforCustomerCare() {

        ModelAndView modelAndView = new ModelAndView("admin/accepted_order");

        try {
            /**
             * Get the Principal object from SecurityContextHolder and populate
             * orderAndCustomerCare.customerCareId
             */
            final EmployeePrincipal employeePrincipal = (EmployeePrincipal) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            modelAndView.addObject("allOrders",
                    customerCareEmployeeService.acceptedOrdersByCustomerCare(employeePrincipal.getId()));

            modelAndView.addObject("availableOrderStatus", orderService.availableOrderStatus());

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return modelAndView;
    }

    @GetMapping(value = "/order/completed")
    public ModelAndView completedOrderView() {
        final ModelAndView modelAndView = new ModelAndView("admin/completed_order");

        try {
            final EmployeePrincipal employeePrincipal = (EmployeePrincipal) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            modelAndView.addObject("allOrders",
                    customerCareEmployeeService.getCompletedOrders(employeePrincipal.getId()));

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return modelAndView;

    }

    @GetMapping(value = "/new-order")
    public ModelAndView createNewOrderView() {

        final ModelAndView modelAndView = new ModelAndView("admin/create_order");

        try {
            modelAndView.addObject("associations", associationService.getAssociationWithStatus(true));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return modelAndView;
    }

}
