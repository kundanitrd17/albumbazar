package com.albumbazaar.albumbazar.controller;

import java.util.List;

import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
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

    private OrderService orderService;
    private Logger logger = LoggerFactory.getLogger(CustomerCareController.class);

    @Autowired
    protected CustomerCareController(@Qualifier("orderService") final OrderService orderService) {
        this.orderService = orderService;
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
            // Process
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return modelAndView;
    }

}
