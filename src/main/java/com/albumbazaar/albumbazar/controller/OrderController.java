package com.albumbazaar.albumbazar.controller;

import java.util.List;

import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(@Qualifier("orderService") OrderService orderService) {

        this.orderService = orderService;
    }

    @GetMapping(value = "/order")
    public String orderView() {

        return "order";
    }

    @PostMapping(value = "/order")
    @ResponseBody
    public String addOrderView(@ModelAttribute OrderDetailForm orderDetail) {

        System.out.println(orderDetail);

        orderService.addOrder(orderDetail);

        return "Done";
    }

    @GetMapping(value = "/order-list")
    public ModelAndView orderListView() {
        final ModelAndView modelAndView = new ModelAndView("all_order");

        modelAndView.addObject("data", orderService.getAllOrder());

        return modelAndView;
    }

    @GetMapping(value = "/order-list/payment")
    @ResponseBody
    public List<OrderDetail> unpaidOrderList(
            @RequestParam(value = "status", defaultValue = "true") String paymentStatus) {

        Boolean status = Boolean.parseBoolean(paymentStatus);
        System.out.println(status);

        return orderService.getOrderByPaymentStatus(status);
    }

}
