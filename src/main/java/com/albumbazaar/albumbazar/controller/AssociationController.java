package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "association")
public final class AssociationController {

    private AssociationService associationService;
    private ProductService productService;

    @Autowired
    public AssociationController(@Qualifier("associationService") final AssociationService associationService,
            @Qualifier("productService") final ProductService productService) {
        this.associationService = associationService;
        this.productService = productService;
    }

    @GetMapping("")
    public ModelAndView associationDashboardView() {
        final ModelAndView modelAndView = new ModelAndView("association/association_home");

        return modelAndView;
    }

}
