package com.albumbazaar.albumbazar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.albumbazaar.albumbazar.Mapper.CustomerMapper;
import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.dto.ResetPasswordDTO;
import com.albumbazaar.albumbazar.model.AvailableRoles;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.principals.CustomerPrincipal;
import com.albumbazaar.albumbazar.principals.EmployeePrincipal;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.GoogleDriveService;
import com.albumbazaar.albumbazar.services.UtilityService;

import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    private CustomerMapper customerMapper;

    private CustomerService customerService;
    private UtilityService utilityService;
    private GoogleDriveService googleDriveService;

    @Autowired(required = true)
    private HomeController(@Qualifier("utilityService") final UtilityService utilityService,
            @Qualifier("googleDriveService") final GoogleDriveService googleDriveService,
            @Qualifier("customerService") final CustomerService customerService, final CustomerMapper customerMapper) {
        this.googleDriveService = googleDriveService;
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.utilityService = utilityService;

    }

    @GetMapping("/")
    public ModelAndView index() {

        final ModelAndView modelAndView = new ModelAndView("index");

        try {

            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                // Get Customer id from the pricipal objects
                final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                if (principal instanceof CustomerPrincipal) {
                    final CustomerPrincipal customerPrincipal = (CustomerPrincipal) principal;
                    Customer customer = customerService.getCustomer(customerPrincipal.getId());
                    CustomerDTO customerDTO = customerMapper.customerEntityToCustomerDTO(customer);

                    modelAndView.addObject("customer", customerDTO);
                }
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return modelAndView;
    }

    @RequestMapping("/login-super")
    public String loginSuper() {
        return "superuser/login_super";
    }

    @RequestMapping("/login-delivery")
    public String loginDelivery() {
        return "login-customer";
    }

    // Need to change this callback url later when I create a separate account for
    // this project itself
    @GetMapping(value = { "/Callback" })
    public RedirectView saveGoogleDriveAuthorizationCode(HttpServletRequest request) throws Exception {

        final RedirectView redirectView = new RedirectView("/");

        final String code = request.getParameter("code");
        String USER_IDENTIFICATION_KEY = new String();
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof CustomerPrincipal) {
            final CustomerPrincipal customerPrincipal = (CustomerPrincipal) principal;
            USER_IDENTIFICATION_KEY = customerPrincipal.getUsername();
        } else if (principal instanceof EmployeePrincipal) {
            final EmployeePrincipal employeePrincipal = (EmployeePrincipal) principal;
            USER_IDENTIFICATION_KEY = employeePrincipal.getUsername();

            if (employeePrincipal.getRole().equalsIgnoreCase(AvailableRoles.Code.ADMIN)) {
                redirectView.setUrl("/admin/new-order");
            } else if (employeePrincipal.getRole().equalsIgnoreCase(AvailableRoles.Code.BRANCH)) {
                redirectView.setUrl("/branch");
            }
        } else {
            request.setAttribute("error", "Unable to Authenticate");
            return redirectView;
        }

        googleDriveService.saveGoogleAuthorizationCode(code, USER_IDENTIFICATION_KEY);

        return redirectView;
    }

    @GetMapping(value = { "/googlesignin" })
    public void doGoogleDriveSignIn(HttpServletResponse response) throws Exception {

        // Redirect to the url provided by google authorization code flow for OAuth
        // sign-in and wait at the callback to get the secret code
        String url = googleDriveService.getRedirectUrlForGoogleSignIn();
        System.out.println(url);
        response.sendRedirect(url);

    }

    @GetMapping(value = "forgot-password")
    public String resetPassword(HttpServletRequest request) {

        return "forgot_password";
    }

    @PostMapping(value = "forgot-password")
    public RedirectView customerResetPassword(@ModelAttribute final ResetPasswordDTO resetPasswordDTO,
            final RedirectAttributes redirectAttributes, final BindingResult bindingResult) {

        final RedirectView redirectView = new RedirectView("/");

        if (bindingResult.hasErrors()) {
            redirectView.setUrl("/forgot-password");
            redirectAttributes.addFlashAttribute("error", "Invalid data!");
            return redirectView;
        }

        try {
            utilityService.resetCustomerPassword(resetPasswordDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            redirectView.setUrl("/forgot-password");
            redirectAttributes.addFlashAttribute("error", "Invalid data!");
        }

        return redirectView;

    }

    @GetMapping(value = "forgot-password/employee")
    public ModelAndView employeeResetPasswordView() {
        final ModelAndView modelAndView = new ModelAndView("/employee_forgot_password");

        return modelAndView;
    }

    @PostMapping(value = "forgot-password/employee")
    public RedirectView employeeResetPassword(@ModelAttribute final ResetPasswordDTO resetPasswordDTO,
            final RedirectAttributes redirectAttributes, final BindingResult bindingResult) {

        final RedirectView redirectView = new RedirectView("/");

        if (bindingResult.hasErrors()) {
            redirectView.setUrl("/forgot-password");
            redirectAttributes.addFlashAttribute("error", "Invalid data!");
            return redirectView;
        }

        try {
            utilityService.resetEmployeePassword(resetPasswordDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            redirectView.setUrl("/forgot-password/employee");
            redirectAttributes.addFlashAttribute("error", "Invalid data!");
        }

        return redirectView;

    }

}
