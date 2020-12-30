package com.albumbazaar.albumbazar.controller;

import java.util.ArrayList;
import java.util.List;

import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.model.Customer;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class test {

    @DeleteMapping("/test/delete")
    public ResponseEntity<?> deleteUser(@RequestBody String id) {
        System.out.println("delete" + id);
        // please delete customer with id id
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public ModelAndView testUpload() {

        ModelAndView mv = new ModelAndView("test");

        List<CustomerDTO> users = new ArrayList<>();

        CustomerDTO c = new CustomerDTO();
        c.setId(1l);
        c.setName("harsh");

        CustomerDTO c1 = new CustomerDTO();
        c1.setId(2l);
        c1.setName("Pratiksha");

        users.add(c1);
        users.add(c);

        // users.add(new Users(3, "data"));

        mv.addObject("users", users);

        return mv;
    }

}

class emp {
    private int id;
    private String name;

    public emp() {
    }

    public emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Users [id=" + id + ", name=" + name + "]";
    }

}
