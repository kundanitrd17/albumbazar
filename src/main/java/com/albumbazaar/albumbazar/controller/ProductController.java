package com.albumbazaar.albumbazar.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    @RequestMapping(value = "/company/{company}")
    public ResponseEntity<Object> getAllProductsAssociated(@PathVariable("company") String company) {
        System.out.println(company);

        return ResponseEntity.ok(new A("10", company, "hi"));
    }

    @PostMapping(value = "/post")
    public ResponseEntity<Object> getData(@RequestBody A a) {
        System.out.println(a);
        return ResponseEntity.ok(a);
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class A {
    String phone;
    String name;
    String id;

    A() {

    }

    A(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "A [id=" + id + ", name=" + name + ", phone=" + phone + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
