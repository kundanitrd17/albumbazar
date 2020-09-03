package com.albumbazaar.albumbazar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/foo")
    @ResponseBody
    public String foo() { return "foo"; }

    @RequestMapping("/login-user")
    public String loginUser() {
        return "loginUser";
    }

    @RequestMapping("/login-super")
    public String loginSuper() {
        return "loginAdmin";
    }

   
    
    @RequestMapping("/errorPage")
    public String errorPage() {
        return "error happended";
    }

}
