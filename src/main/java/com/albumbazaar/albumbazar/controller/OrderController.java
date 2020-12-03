package com.albumbazaar.albumbazar.controller;

import java.util.HashMap;
import java.util.List;

import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

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
    public String addNewOrder(@ModelAttribute OrderDetailForm orderDetail) {
        logger.info("A new Order was Made");
        System.out.println(orderDetail);

        try {
            orderService.addOrder(orderDetail);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "Not Done";
        }

        return "Done";
    }

    @GetMapping(value = "/order-list")
    public ModelAndView orderListView(@RequestParam(value = "payment", defaultValue = "") String paymentStatus,
            @RequestParam(value = "status", defaultValue = "completed") String orderStatus) {

        System.out.println(paymentStatus.isBlank());

        final ModelAndView modelAndView = new ModelAndView("all_order");

        // If payment Option is specified than only send payment info related order
        // detail
        if (!paymentStatus.isBlank()) {
            try {
                modelAndView.addObject("data",
                        orderService.getOrderByPaymentStatus(Boolean.parseBoolean(paymentStatus)));
            } catch (Exception e) {
                modelAndView.addObject("data", null);
            }

            return modelAndView;
        }

        // If payment status is not specified then send order details based on order
        // status
        try {
            modelAndView.addObject("data", orderService.getAllOrderWithStatus(orderStatus));
        } catch (Exception e) {
            modelAndView.addObject("data", null);
        }

        return modelAndView;
    }

    @GetMapping(value = "orders/pool")
    @ResponseBody
    protected ResponseEntity<?> orderPoolArea() {

        List<OrderDetail> pendingOrders = orderService.getOrdersWithStatus(OrderDetailStatus.PENDING);
        HashMap<String, List<OrderDetail>> orders = new HashMap<>();
        orders.put("order", pendingOrders);

        System.out.println(pendingOrders.size());

        return ResponseEntity.ok().body(orders);
    }

}
