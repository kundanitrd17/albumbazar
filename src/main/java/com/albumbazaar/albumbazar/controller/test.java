package com.albumbazaar.albumbazar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {

    @GetMapping("/test")
    public String testUpload() {
        return "abc";
    }

}
