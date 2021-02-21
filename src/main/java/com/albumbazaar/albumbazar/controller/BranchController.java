package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.services.AssociationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/branch")
@Slf4j
public class BranchController {

    private final AssociationService associationService;

    @Autowired(required = true)
    protected BranchController(
        @Qualifier("associationService") final AssociationService associationService
    ) {
        this.associationService = associationService;
    }


    @GetMapping("")
    public ModelAndView branchHomeView() {
        final ModelAndView modelAndView = new ModelAndView("branch/branch_home");

        try {
            modelAndView.addObject("associations", associationService.getAssociationWithStatus(true));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return modelAndView;
    }

    @GetMapping(value = "/login")
    public String branchLoginView() {

        return "branch/branch_login";
    }

}
