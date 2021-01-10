package com.albumbazaar.albumbazar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/branch")
public class BranchController {

    @GetMapping("")
    public ModelAndView branchHomeView() {
        final ModelAndView modelAndView = new ModelAndView("branch/branch_home");

        return modelAndView;
    }

    @GetMapping(value = "/login")
    public String branchLoginView() {

        return "branch/branch_login";
    }

}
