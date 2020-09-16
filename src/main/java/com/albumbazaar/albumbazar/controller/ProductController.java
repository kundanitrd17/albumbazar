package com.albumbazaar.albumbazar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    @RequestMapping(value = "/company/{company}")
    public ResponseEntity getAllProductsAssociated(@PathVariable("company")String company) {
        System.out.println(company);


        return new ResponseEntity(String.format("Hello %s!", company), HttpStatus.ACCEPTED);
    }


}

