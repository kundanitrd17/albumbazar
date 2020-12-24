package com.albumbazaar.albumbazar.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.services.CustomerCareEmployeeService;
import com.albumbazaar.albumbazar.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "customer-care")
public final class CustomerCareController {
    private Logger logger = LoggerFactory.getLogger(CustomerCareController.class);

    private final CustomerCareEmployeeService customerCareEmployeeService;
    private final OrderService orderService;

    @Autowired
    protected CustomerCareController(@Qualifier("orderService") final OrderService orderService,
            @Qualifier("customerCareEmployeeService") final CustomerCareEmployeeService customerCareEmployeeService) {
        this.orderService = orderService;
        this.customerCareEmployeeService = customerCareEmployeeService;
    }

    @GetMapping(value = "")

    public ModelAndView customerCareHomeView(Model model) {
        final ModelAndView modelAndView = new ModelAndView("customercare/dashboard_customer_care");

        return modelAndView;
    }

    @GetMapping(value = "order-pool")
    public ModelAndView customerCareOrderPool(Model model) {
        final ModelAndView modelAndView = new ModelAndView("customercare/customercare_order_pool");

        try {
            final List<OrderDetail> recentlyReceivedOrders = orderService
                    .getOrdersWithStatus(OrderDetailStatus.PENDING);

            modelAndView.addObject("recentlyReceivedOrders", recentlyReceivedOrders);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return modelAndView;

    }

    @GetMapping(value = "accepted-order")
    public ModelAndView acceptedOrdersforCustomerCare() {

        ModelAndView modelAndView = new ModelAndView("customercare/customercare_accepted_order");

        try {
            /**
             * Get the Principal object from SecurityContextHolder and populate
             * orderAndCustomerCare.customerCareId
             */
            // Object principal = SecurityContextHolder.getContext().getAuthentication()
            // .getPrincipal();

            modelAndView.addObject("allOrders", customerCareEmployeeService.acceptedOrdersByCustomerCare(1l));

            modelAndView.addObject("availableOrderStatus", orderService.availableOrderStatus());

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return modelAndView;
    }

    @GetMapping(value = "completed-order")
    public ModelAndView completedOrderView() {
        final ModelAndView modelAndView = new ModelAndView("customercare/customer_care_completed");

        try {
            // change this later
            // Get customer care 's id from security context object i.e. principal object

            final Long customerCareId = 1l;

            modelAndView.addObject("allOrders", customerCareEmployeeService.getCompletedOrders(customerCareId));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return modelAndView;

    }

}
