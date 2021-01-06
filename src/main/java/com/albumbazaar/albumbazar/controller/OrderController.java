package com.albumbazaar.albumbazar.controller;

import java.util.HashMap;
import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import com.albumbazaar.albumbazar.form.order.OrderDetailForm;
import com.albumbazaar.albumbazar.form.order.OrderDetailFormDTO;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.principals.CustomerPrincipal;
import com.albumbazaar.albumbazar.services.GoogleDriveService;
import com.albumbazaar.albumbazar.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public final class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;
    private GoogleDriveService googleDriveService;

    @Autowired
    public OrderController(@Qualifier("orderService") OrderService orderService,
            @Qualifier("googleDriveService") final GoogleDriveService googleDriveService) {

        this.googleDriveService = googleDriveService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/order")
    public String orderView(HttpServletRequest request) {

        System.out.println(request.getRequestURL());
        // authorize google drive here

        return "redirect:/";
    }

    @PostMapping(value = "/customer/order")
    public RedirectView addNewOrder(@ModelAttribute OrderDetailFormDTO orderDetailFormDTO,
            final RedirectAttributes redirectAttributes) {

        final RedirectView redirectView = new RedirectView("/customer/order/upload-photo");

        logger.info("A new Order was Made");
        System.out.println(orderDetailFormDTO);

        try {
            // throw new RuntimeException("message");
            final Object customerObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (customerObj instanceof CustomerPrincipal) {
                final CustomerPrincipal customerPrincipal = (CustomerPrincipal) customerObj;

                final OrderDetail order = orderService.createNewOrder(orderDetailFormDTO, customerPrincipal.getId());
                redirectAttributes.addFlashAttribute("order_id", order.getId());
            } else {
                throw new AuthenticationException();
            }

        } catch (AuthenticationException e) {
            redirectAttributes.addAttribute("error", "Unauthorized User");
            redirectView.setUrl("/");
        } catch (Exception e) {
            logger.error(e.getMessage());

            redirectAttributes.addAttribute("error", "Unable to make order");
            redirectView.setUrl("/");
            return redirectView;
        }

        return redirectView;
    }

    @GetMapping("/customer/order/upload-photo")
    public ModelAndView uploadPhotoView(Model model) {
        final ModelAndView modelAndView = new ModelAndView("upload_photos");

        if (model.getAttribute("order_id") == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        modelAndView.addObject("order_id", model.getAttribute("order_id"));
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
