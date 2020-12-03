package com.albumbazaar.albumbazar.controller;

import java.util.List;

import com.albumbazaar.albumbazar.form.BasicBranchInfoForm;
import com.albumbazaar.albumbazar.form.ForgotPasswordFormSuperuser;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.form.association.AssociationDetailForm;
import com.albumbazaar.albumbazar.model.Branch;
import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.BranchService;
import com.albumbazaar.albumbazar.services.EmployeeService;
import com.albumbazaar.albumbazar.services.SuperuserService;

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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/superuser")
public class SuperuserController {

    private final SuperuserService superuserDetailsService;
    private final BranchService branchService;
    private final EmployeeService employeeService;
    private final AssociationService associationService;

    @Autowired
    public SuperuserController(@Qualifier("superuserService") SuperuserService superuserDetailsService,
            @Qualifier("branchService") BranchService branchService,
            @Qualifier("employeeService") EmployeeService employeeService,
            @Qualifier("associationService") AssociationService associationService) {
        this.superuserDetailsService = superuserDetailsService;
        this.branchService = branchService;
        this.employeeService = employeeService;
        this.associationService = associationService;
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
        // System.out.println(allBranch);
        modelAndView.addObject("branches", allBranch);

        return modelAndView;
    }

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

}