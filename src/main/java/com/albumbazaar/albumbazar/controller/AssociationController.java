
package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.services.AssociationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "association")

public final class AssociationController {

    private Logger logger = LoggerFactory.getLogger(AssociationController.class);

    private AssociationService associationService;

    @Autowired
    public AssociationController(@Qualifier("associationService") final AssociationService associationService) {
        this.associationService = associationService;
    }

    @PostMapping("/dp/change")
    public RedirectView changeProfilePhoto(@RequestParam("photo") final MultipartFile photoFile,
            final RedirectAttributes redirectAttributes) {

        final RedirectView redirectView = new RedirectView("/association");

        try {
            // Get the association id from security context
            Long id = 1l;
            associationService.changeProfilePhoto(photoFile, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            redirectAttributes.addAttribute("error", "Unable to change photo");
        }

        return redirectView;
    }

    @GetMapping("")
    public ModelAndView associationDashboardView() {
        final ModelAndView modelAndView = new ModelAndView("association/association_home");

        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView associationLoginView(final Model model) {
        final ModelAndView modelAndView = new ModelAndView("association/association_login");

        return modelAndView;
    }

    @GetMapping("/order-list/new-arrived-order")
    public ModelAndView newlyArrivedOrderView() {
        final ModelAndView modelAndView = new ModelAndView("association/newly_arrived_orders");

        modelAndView.addObject("recentlyReceivedOrders", associationService.getAllNewlyArrivedOrders(1l));

        return modelAndView;
    }

    @GetMapping("/order-list/processing")
    public ModelAndView processingOrderListView() {
        final ModelAndView modelAndView = new ModelAndView("association/under_process_orders");

        modelAndView.addObject("allOrders", associationService.getUnderProcessOrders(1l));

        return modelAndView;
    }

    @GetMapping("/order-list/ready-to-deliver")
    public ModelAndView readyToDeliverOrderListView() {
        final ModelAndView modelAndView = new ModelAndView("association/ready_to_deliver");

        modelAndView.addObject("allOrders", associationService.getReadyToDeliverOrders(1l));

        return modelAndView;
    }

    @GetMapping("/order-list/completed")
    public ModelAndView completedOrderListView() {
        final ModelAndView modelAndView = new ModelAndView("association/completed_orders");

        modelAndView.addObject("allOrders", associationService.getCompletedOrder(1l));

        return modelAndView;
    }

}