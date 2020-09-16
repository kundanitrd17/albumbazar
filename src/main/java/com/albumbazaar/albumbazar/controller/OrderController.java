package com.albumbazaar.albumbazar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @GetMapping(value = "/order")
    public String orderView() {

        return "order";
    }

    @PostMapping(value = "/order")
    @ResponseBody
    public String addOrderView() {

        return "Done";
    }


}
