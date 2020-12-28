package com.albumbazaar.albumbazar.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public final class OrderController {

    private OrderService orderService;
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(@Qualifier("orderService") OrderService orderService) {

        this.orderService = orderService;
    }

    @GetMapping(value = "/order")
    public String orderView(HttpServletRequest request) {

        System.out.println(request.getRequestURL());
        // authorize google drive here

        return "redirect:/";
    }

    @PostMapping(value = "/order")
    public RedirectView addNewOrder(@ModelAttribute OrderDetailForm orderDetail,
            final RedirectAttributes redirectAttributes) {

        final RedirectView redirectView = new RedirectView("/order/upload-photo");

        logger.info("A new Order was Made");
        System.out.println(orderDetail);

        try {
            // throw new RuntimeException("message");
            final OrderDetail order = orderService.addOrder(orderDetail);
            redirectAttributes.addFlashAttribute("order_id", order.getId());

        } catch (Exception e) {
            logger.error(e.getMessage());

            redirectAttributes.addAttribute("error", true);
            redirectView.setUrl("order");
            return redirectView;
        }

        return redirectView;
    }

    @GetMapping("/order/upload-photo")
    public ModelAndView uploadPhotoView(Model model) {
        final ModelAndView modelAndView = new ModelAndView("upload_photos");

        if (model.getAttribute("order_id") == null) {
            modelAndView.setViewName("redirect:/order");
            return modelAndView;
        }
        modelAndView.addObject("order_id", model.getAttribute("order_id"));

        System.out.println(modelAndView);
        System.out.println(model);

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
