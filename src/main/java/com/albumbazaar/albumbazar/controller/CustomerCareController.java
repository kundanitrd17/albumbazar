package com.albumbazaar.albumbazar.controller;

import java.util.List;

import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "customer-care")
public class CustomerCareController {

    private OrderService orderService;

    @Autowired
    protected CustomerCareController(@Qualifier("orderService") final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "")
    @ResponseBody
    public String customerCareHomeView(Model model) {

        return "home customer";
    }

    @GetMapping(value = "order-pool")
    public ModelAndView customerCareOrderPool(Model model) {
        final ModelAndView modelAndView = new ModelAndView("customercare/customercare_order_pool");
        System.err.println("Here");

        try {
            final List<OrderDetail> recentlyReceivedOrders = orderService
                    .getOrdersWithStatus(OrderDetailStatus.PENDING);
            // System.out.println(recentlyReceivedOrders.get(0));
            modelAndView.addObject("recentlyReceivedOrders", recentlyReceivedOrders);
        } catch (Exception e) {
            System.out.println("recently Received Orders");
        }

        return modelAndView;

    }

}
