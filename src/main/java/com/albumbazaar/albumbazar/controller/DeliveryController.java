package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.services.DeliveryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/delivery")
public final class DeliveryController {

    private final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    private final DeliveryService deliveryService;

    @Autowired
    DeliveryController(@Qualifier("deliveryService") final DeliveryService deliveryService) {

        this.deliveryService = deliveryService;
    }

    @GetMapping(value = "")
    public ModelAndView homePageAndRecentlyReceivedView() {
        final ModelAndView modelAndView = new ModelAndView("/delivery/delivery_newly_arrived");

        try {
            modelAndView.addObject("deliveries", deliveryService.recentlyReceivedOrUnseenDeliveries());
        } catch (Exception e) {
            logger.error(e.getMessage());
            modelAndView.addObject("error", "No Deliveries Found!...");
        }

        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView deliveryLoginView(final Model model) {

        final ModelAndView modelAndView = new ModelAndView("/delivery/delivery_login");

        return modelAndView;
    }

    @GetMapping(value = { "/undelivered" })
    public ModelAndView getAllOrdersToDeliver() {
        final ModelAndView modelAndView = new ModelAndView("/delivery/delivery_undelivered");

        try {
            modelAndView.addObject("deliveries", deliveryService.undeliveredOrders());
        } catch (Exception e) {
            modelAndView.addObject("error", "Unable to fetch orders");
            logger.error(e.getMessage());
        }

        return modelAndView;
    }

    @GetMapping(value = { "/delivered" })
    public ModelAndView deliveryCompleted() {

        final ModelAndView modelAndView = new ModelAndView("/delivery/delivery_completed");

        try {
            modelAndView.addObject("deliveries", deliveryService.completedDeliveries());
        } catch (Exception e) {
            modelAndView.addObject("error", "Unable to fetch orders");
            logger.error(e.getMessage());
        }

        return modelAndView;
    }

}
