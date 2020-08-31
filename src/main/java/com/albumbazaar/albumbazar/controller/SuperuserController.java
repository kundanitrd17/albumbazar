package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.dao.SuperuserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuperuserController {

    @Autowired
    private SuperuserRepository superuserRepo;
    
    @GetMapping("/superuser")
    public String index() {

        System.out.println(superuserRepo.findAll());

        return "superuser";
    }

}