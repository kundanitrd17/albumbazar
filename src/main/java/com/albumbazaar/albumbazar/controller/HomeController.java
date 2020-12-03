package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.dao.BranchRepository;
import com.albumbazaar.albumbazar.dao.EmployeeRepository;
import com.albumbazaar.albumbazar.dao.SuperuserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private SuperuserRepository repo;

    @Autowired
    private EmployeeRepository erepo;
    @Autowired
    private BranchRepository brepo;

    @PostMapping("/emp")
    @ResponseBody
    public String emp() {

        return "Added";
    }

    @GetMapping("/")
    public String home() {

        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/foo")
    @ResponseBody
    public String foo() {
        return "foo";
    }

    @RequestMapping("/login-user")
    public String loginUser() {
        return "loginUser";
    }

    @RequestMapping("/login-super")
    public String loginSuper() {
        return "superuser/login-super";
    }

}