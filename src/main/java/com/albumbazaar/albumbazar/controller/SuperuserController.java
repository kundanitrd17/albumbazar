package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.dao.SuperuserRepository;

import com.albumbazaar.albumbazar.form.ForgotPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/superuser")
public class SuperuserController {


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {

        System.out.println("Superuser controller");

        return "superuser";
    }

}