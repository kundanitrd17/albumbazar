package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.form.BasicBranchInfoForm;
import com.albumbazaar.albumbazar.form.ForgotPasswordFormSuperuser;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.services.BranchService;
import com.albumbazaar.albumbazar.services.SuperuserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/superuser")
public class SuperuserController {

    private final SuperuserDetailsService superuserDetailsService;
    private final BranchService branchService;

    @Autowired
    public SuperuserController(@Qualifier("superuserDetailsService") SuperuserDetailsService superuserDetailsService,
                @Qualifier("branchService") BranchService branchService) {
        this.superuserDetailsService = superuserDetailsService;
        this.branchService = branchService;
    }

    @RequestMapping(value = "/api/resetsuperuser", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap hello(@ModelAttribute ForgotPasswordFormSuperuser forgotPasswordForm) {
        System.out.println("Hello password " + forgotPasswordForm.getPassword1() + forgotPasswordForm.getPassword2());
        System.out.println(forgotPasswordForm);

        return superuserDetailsService.resetPassword(forgotPasswordForm);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {

        System.out.println("Superuser controller");

        return "superuser/super-admin";
    }

    @RequestMapping(value = "add-branch", method = RequestMethod.GET)
    public String viewaddBranch() {
        return "add-branch";
    }

    @PostMapping(value = "add-branch")
    @ResponseBody
    public String addBranch(@ModelAttribute BasicBranchInfoForm branchDetails, @ModelAttribute LocationForm locationForm) {

        System.out.println(branchDetails);
        System.out.println(locationForm);
        
        branchService.addBranch(branchDetails, locationForm); // call the add branch service

        return "Added branch";
    }

    @GetMapping(value = "list-branch")
    public String getAllBranch() {
        branchService.getAllBranch();

        return "branch-list";
    }

}