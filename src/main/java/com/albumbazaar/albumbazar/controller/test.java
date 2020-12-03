package com.albumbazaar.albumbazar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class test {

    @GetMapping(value = "test/foo")
    public String viewtest() {
        return "abc";
    }

    @GetMapping(value = "/api/hello")
    @ResponseBody
    public ResponseEntity<?> hello() {
        HelloMessage h = new HelloMessage();
        h.setName("Hello Buddy");

        return ResponseEntity.ok().body(h);
    }

}

class HelloMessage {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
