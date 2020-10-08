package com.albumbazaar.albumbazar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.ProductService;
import com.albumbazaar.albumbazar.utilities.AllProducts;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/product")
public class ProductControllerAPI {

    private AssociationService associationService;
    private ProductService productService;

    @Autowired
    public ProductControllerAPI(@Qualifier("associationService") AssociationService associationService,
            @Qualifier("productService") ProductService productService) {
        this.productService = productService;
        this.associationService = associationService;
    }

    @GetMapping(value = "/company")
    public ResponseEntity<Object> getCompanies() {
        final List<HashMap<String, String>> associationNames = associationService.getAllAssociation().stream()
                .map(association -> {
                    // System.out.println(association);
                    HashMap<String, String> obj = new HashMap<>(5);
                    obj.put("id", String.valueOf(association.getId()));
                    obj.put("name", association.getName());
                    return obj;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(associationNames);
    }

    @GetMapping(value = "/company/{companyId}")
    public ResponseEntity<Object> getAllProductsAssociated(@PathVariable("companyId") String companyId) {
        System.out.println(companyId);

        AllProducts products = productService.getAllProducts(companyId);
        System.out.println(products);
        System.out.println(companyId);

        return ResponseEntity.ok(products);
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
