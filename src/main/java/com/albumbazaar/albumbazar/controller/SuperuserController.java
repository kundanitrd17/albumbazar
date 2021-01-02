package com.albumbazaar.albumbazar.controller;

import java.util.List;
import java.util.NoSuchElementException;

import com.albumbazaar.albumbazar.principals.SuperuserPrincipal;
import com.albumbazaar.albumbazar.dto.ErrorDTO;
import com.albumbazaar.albumbazar.form.BasicBranchInfoForm;
import com.albumbazaar.albumbazar.form.ForgotPasswordFormSuperuser;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.association.AssociationDetailForm;
import com.albumbazaar.albumbazar.form.employee.BasicEmployeeDetailForm;
import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.model.Branch;
import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.BranchService;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.EmployeeService;
import com.albumbazaar.albumbazar.services.OrderService;
import com.albumbazaar.albumbazar.services.SuperuserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/superuser")
public final class SuperuserController {

    private Logger logger = LoggerFactory.getLogger(SuperuserController.class);

    private final OrderService orderService;
    private final SuperuserService superuserDetailsService;
    private final BranchService branchService;
    private final EmployeeService employeeService;
    private final AssociationService associationService;
    private final CustomerService customerService;

    @Autowired
    public SuperuserController(@Qualifier("superuserService") SuperuserService superuserDetailsService,
            @Qualifier("branchService") BranchService branchService,
            @Qualifier("employeeService") EmployeeService employeeService,
            @Qualifier("associationService") AssociationService associationService,
            @Qualifier("customerService") final CustomerService customerService,
            @Qualifier("orderService") final OrderService orderService) {
        this.superuserDetailsService = superuserDetailsService;
        this.branchService = branchService;
        this.employeeService = employeeService;
        this.associationService = associationService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {

        logger.info("Superuser Logged in now");

        SuperuserPrincipal principal = (SuperuserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        System.out.println(principal);

        return "superuser/super-admin";
    }

    @RequestMapping(value = "/api/resetsuperuser", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap hello(@ModelAttribute ForgotPasswordFormSuperuser forgotPasswordForm) {
        System.out.println("Hello password " + forgotPasswordForm.getPassword1() + forgotPasswordForm.getPassword2());
        System.out.println(forgotPasswordForm);

        return superuserDetailsService.resetPassword(forgotPasswordForm);
    }

    // Association Endpoints

    @GetMapping(value = "add-association")
    public String viewAddAssociation() {

        return "superuser/add-association";
    }

    @PostMapping(value = "add-association")
    @ResponseBody
    public String addAssociation(@ModelAttribute AssociationDetailForm associationDetail) {
        System.out.println(associationDetail);
        associationService.addAssociation(associationDetail);

        return "added";
    }

    @GetMapping(value = "/association")
    @ResponseBody
    public ModelAndView home() {
        final ModelAndView modelAndView = new ModelAndView("superuser/association_list");
        List<Association> associations;
        try {
            associations = associationService.getAllAssociation();
        } catch (Exception e) {
            associations = null;
        }
        modelAndView.addObject("associations", associations);
        return modelAndView;
    }

    // Superuser Branch Endpoints

    @RequestMapping(value = "add-branch", method = RequestMethod.GET)
    public String viewaddBranch() {
        return "superuser/add-branch";
    }

    @PostMapping(value = "add-branch")
    @ResponseBody
    public String addBranch(@ModelAttribute BasicBranchInfoForm branchDetails,
            @ModelAttribute LocationForm locationForm) {

        System.out.println(branchDetails);
        System.out.println(locationForm);

        branchService.addBranch(branchDetails, locationForm); // call the add branch service

        return "Added branch";
    }

    @GetMapping(value = "list-branch")
    public ModelAndView getAllBranch() {
        ModelAndView modelAndView = new ModelAndView("superuser/branch_list");
        List<Branch> allBranch = branchService.getAllBranch().get();

        modelAndView.addObject("branches", allBranch);

        return modelAndView;
    }

    // EndPoints for customer controller

    @GetMapping(value = "/customer")
    public ModelAndView getAllCustomer() {

        final ModelAndView modelAndView = new ModelAndView("superuser/customer");

        modelAndView.addObject("customers", customerService.getAllCustomer());

        return modelAndView;
    }

    @GetMapping(value = "/customer/discounted")
    public ModelAndView getDiscountedCustomer() {
        final ModelAndView modelAndView = new ModelAndView("superuser/customer");
        modelAndView.addObject("customers", customerService.getDiscountedCustomer());

        return modelAndView;
    }

    @GetMapping(value = "/customer/blocked")
    public ModelAndView getBlockedCustomer() {
        final ModelAndView modelAndView = new ModelAndView("superuser/customer");
        modelAndView.addObject("customers", customerService.getBlockeList());

        return modelAndView;
    }

    // Employee related endpoints
    @GetMapping(value = "/employee-list")
    public ModelAndView allEmployeeView() {
        ModelAndView modelAndView = new ModelAndView("/superuser/emp-list");

        try {
            modelAndView.addObject("employees", employeeService.getAllEmployee());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return modelAndView;
    }

    @GetMapping(value = "/employee-add")
    public ModelAndView viewAddEmployee() {
        final ModelAndView modelAndView = new ModelAndView("/superuser/add-emp");

        try {
            modelAndView.addObject("active_branches", branchService.getAllActiveBranchName());
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());

            final ErrorDTO error = new ErrorDTO();
            error.setMessage("No Branch Available");
            modelAndView.addObject("error", error);

        }

        modelAndView.addObject("employee_roles", employeeService.getAllAvailableEmployeeRole());

        return modelAndView;
    }

    @PostMapping(value = "/add-employee")
    @ResponseBody
    public String addEmployee(@ModelAttribute BasicEmployeeDetailForm employeeDetail,
            @ModelAttribute LocationForm addressDetail) {

        employeeService.addEmployee(employeeDetail, addressDetail);

        return "Added Employee";
    }

    // Orders endpoints for superuser

    @GetMapping(value = "/order-list")
    public ModelAndView orderListView(@RequestParam(value = "payment", defaultValue = "") String paymentStatus,
            @RequestParam(value = "status", defaultValue = "completed") String orderStatus) {

        System.out.println(paymentStatus.isBlank());

        final ModelAndView modelAndView = new ModelAndView("/superuser/all_order");

        // If payment Option is specified than only send payment info related order
        // detail
        if (!paymentStatus.isBlank()) {
            try {
                modelAndView.addObject("order_details",
                        orderService.getOrderByPaymentStatus(Boolean.parseBoolean(paymentStatus)));
            } catch (Exception e) {
                logger.error(e.getMessage());
                modelAndView.addObject("order_details", null);
            }

            return modelAndView;
        }

        // If payment status is not specified then send order details based on order
        // status
        try {
            modelAndView.addObject("order_details", orderService.getAllOrderWithStatus(orderStatus));
        } catch (Exception e) {
            logger.error(e.getMessage());
            modelAndView.addObject("order_details", null);
        }

        return modelAndView;
    }
}