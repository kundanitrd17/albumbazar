package com.albumbazaar.albumbazar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping(value = "")
    public String adminView() {
        return "admin/admin_dashboard";
    }

    @GetMapping(value = "add-employee")
    public String viewAddEmployee() {
        return "admin_add_emp";
    }

}
