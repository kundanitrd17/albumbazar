package com.albumbazaar.albumbazar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "association")
public class AssociationController {

    private AssociationService associationService;
    private ProductService productService;

    @Autowired
    public AssociationController(@Qualifier("associationService") final AssociationService associationService,
            @Qualifier("productService") final ProductService productService) {
        this.associationService = associationService;
        this.productService = productService;
    }

    @GetMapping(value = "")
    @ResponseBody
    public ModelAndView home() {
        final ModelAndView modelAndView = new ModelAndView("superuser/association_list");
        List<Association> associations;
        try {
            associations = associationService.getAllAssociation();
        } catch (Exception e) {
            associations = null;
        }
        modelAndView.addObject("associations", associations);
        return modelAndView;
    }

    @GetMapping(value = "all_association_product_view")
    public ModelAndView allAssociationProductView() {
        final ModelAndView modelAndView = new ModelAndView("superuser/association_product_view");

        try {
            final List<Association> associations = associationService.getAssociationWithStatus(true).stream()
                    .map(association -> {
                        Association eachAssociation = new Association();
                        eachAssociation.setId(association.getId());
                        eachAssociation.setName(association.getName());
                        return eachAssociation;
                    }).collect(Collectors.toList());

            modelAndView.addObject("associations", associations);
        } catch (Exception e) {
            // Send some error msg
        }

        return modelAndView;

    }

}
