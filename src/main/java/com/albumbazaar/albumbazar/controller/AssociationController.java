package com.albumbazaar.albumbazar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/association")
public class AssociationController {

    @GetMapping(value = "")
    public String home() {

        return "association/association";
    }

}
