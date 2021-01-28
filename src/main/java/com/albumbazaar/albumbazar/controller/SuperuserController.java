package com.albumbazaar.albumbazar.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.dto.BranchDTO;
import com.albumbazaar.albumbazar.dto.EmployeeDTO;
import com.albumbazaar.albumbazar.dto.ErrorDTO;
import com.albumbazaar.albumbazar.form.BasicBranchInfoForm;
import com.albumbazaar.albumbazar.form.ForgotPasswordFormSuperuser;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.association.AssociationDetailForm;
import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.model.Branch;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.principals.SuperuserPrincipal;
import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.BranchService;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.EmployeeService;
import com.albumbazaar.albumbazar.services.OrderService;
import com.albumbazaar.albumbazar.services.SuperuserService;
import com.albumbazaar.albumbazar.services.UtilityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
    private final UtilityService utilityService;

    @Autowired
    public SuperuserController(@Qualifier("superuserService") SuperuserService superuserDetailsService,
            @Qualifier("branchService") BranchService branchService,
            @Qualifier("employeeService") EmployeeService employeeService,
            @Qualifier("associationService") AssociationService associationService,
            @Qualifier("customerService") final CustomerService customerService,
            @Qualifier("utilityService") final UtilityService utilityService,
            @Qualifier("orderService") final OrderService orderService) {
        this.superuserDetailsService = superuserDetailsService;
        this.branchService = branchService;
        this.employeeService = employeeService;
        this.associationService = associationService;
        this.customerService = customerService;
        this.orderService = orderService;
        this.utilityService = utilityService;
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

    @PostMapping(value = "/add-branch")
    public RedirectView addBranch(@ModelAttribute BranchDTO branchDetails, @ModelAttribute AddressDTO locationForm,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

        final RedirectView redirectView = new RedirectView("/superuser/add-branch");

        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("error", "Invalid Inputs");
            return redirectView;
        }

        try {
            branchService.addBranch(branchDetails, locationForm);
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", "Unable to create branch! Invalid data");
            logger.error(e.getMessage());
        }

        return redirectView;
    }

    @GetMapping(value = "list-branch")
    public ModelAndView getAllBranch() {
        ModelAndView modelAndView = new ModelAndView("superuser/branch_list");
        List<Branch> allBranch = branchService.getAllBranch().get();

        modelAndView.addObject("branches", allBranch);

        return modelAndView;
    }

    @PostMapping(value = "/branch/address/change")
    public RedirectView updateAddress(@ModelAttribute @Valid final AddressDTO addressDTO,
            @RequestParam("branchId") final Long branchId, final RedirectAttributes redirectAttributes,
            final BindingResult bindingResult) {

        final RedirectView redirectView = new RedirectView("/superuser/list-branch");
        if (bindingResult.hasErrors()) {

            redirectAttributes.addAttribute("error", "Invalid data");
            return redirectView;
        }

        try {
            branchService.updateAddressInfo(addressDTO, branchId);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return redirectView;
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

    @GetMapping(value = "/employee/add")
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

    @PostMapping(value = "/employee/add")
    // @ResponseBody
    public String addEmployee(@Valid @ModelAttribute EmployeeDTO employeeDetail,
            @ModelAttribute LocationForm addressDetail, final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/employee-add";
        }
        System.out.println(employeeDetail);
        // employeeService.addEmployee(employeeDetail, addressDetail);
        try {
            employeeService.createEmployee(employeeDetail);
        } catch (Exception e) {
            logger.error(e.getMessage());
            redirectAttributes.addAttribute("error", true);
        }

        return "redirect:/superuser/employee/add";
    }

    @PostMapping(value = "/employee/address/update")
    public RedirectView updateEmployeeAddress(@ModelAttribute @Valid final AddressDTO addressDTO,
            @RequestParam("employeeId") final Long employeeId, final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/superuser/employee-list");

        if (bindingResult.hasErrors()) {
            redirectAttributes.addAttribute("error", "Invalid Data");
            return redirectView;
        }

        try {
            employeeService.updateAddressInfo(addressDTO, employeeId);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return redirectView;
    }

    // Orders endpoints for superuser

    @GetMapping(value = "/order-list")
    public ModelAndView orderListView(@RequestParam(value = "payment", defaultValue = "") String paymentStatus,
            @RequestParam(value = "status", defaultValue = "completed") final String orderStatus) {

        System.out.println(paymentStatus.isBlank());

        final ModelAndView modelAndView = new ModelAndView("/superuser/all_order");

        // If payment Option is specified than only send payment info related order
        // detail
        if (!paymentStatus.isBlank()) {
            try {

                final boolean isPaid = Boolean.parseBoolean(paymentStatus);

                if (isPaid)
                    modelAndView.addObject("title", "Paid Orders");
                else
                    modelAndView.addObject("title", "UnPaid Orders");

                modelAndView.addObject("order_details", orderService.getOrderByPaymentStatus(isPaid));
            } catch (Exception e) {
                logger.error(e.getMessage());
                modelAndView.addObject("order_details", null);
            }

            return modelAndView;
        }

        modelAndView.addObject("title", orderStatus.toString());
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

    // Reward
    @GetMapping(value = "/reward")
    public ModelAndView rewardView() {
        final ModelAndView modelAndView = new ModelAndView("/superuser/rewards_and_discount");

        try {
            final List<Customer> customers = customerService.getAllCustomer();
            modelAndView.addObject("customers", customers);

            modelAndView.addObject("website_info", superuserDetailsService.getWebsiteInfoEntity());

        } catch (Exception e) {
            logger.error(e.getMessage());
            modelAndView.addObject("error", "Error Reload Page");
        }

        return modelAndView;
    }

    // Set Referral amount
    @PostMapping(value = "/reward/referral")
    public RedirectView setReferralReward(@RequestParam("amount") final Double amount) {
        final RedirectView redirectView = new RedirectView("/superuser/reward");

        try {
            superuserDetailsService.updateReferallAmount(amount);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return redirectView;

    }

    // Set special discount for a customer
    @PostMapping(value = "/reward/discount/customer")
    public RedirectView setDiscountForCustomer(@RequestParam("customerId") final String customerIdentifier,
            @RequestParam("amount") final Double amount, final RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/superuser/reward");

        try {
            customerService.setRewardForCustomer(customerIdentifier, amount);
        } catch (NumberFormatException e) {
            redirectAttributes.addAttribute("error", "Invalid Inputs");
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", "Unable to update rewards");
        }

        return redirectView;

    }

    // Set discount for all the customer
    @PostMapping(value = "/reward/discount/global")
    public RedirectView setDiscountForAll(@RequestParam("amount") final Double amount) {
        final RedirectView redirectView = new RedirectView("/superuser/reward");

        try {
            superuserDetailsService.updateGlobalDiscount(amount);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return redirectView;

    }

}