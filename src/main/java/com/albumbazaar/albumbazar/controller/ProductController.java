package com.albumbazaar.albumbazar.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.utilities.AddCoverForm;
import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.ProductService;
import com.albumbazaar.albumbazar.utilities.AddPaperForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public final class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private AssociationService associationService;
    private ProductService productService;

    @Autowired
    public ProductController(@Qualifier("associationService") AssociationService associationService,
            @Qualifier("productService") ProductService productService) {
        this.productService = productService;
        this.associationService = associationService;
    }

    // view for Add product associated to a association
    @GetMapping(value = "/superuser/product/add")
    public ModelAndView addProductsView() {
        final ModelAndView modelAndView = new ModelAndView("/superuser/add_asso_prod_list");

        final List<Association> associations = associationService.getAllAssociation();
        modelAndView.addObject("availableAssociations", associations);

        System.out.println(associations);

        return modelAndView;
    }

    // Persist new Data for a associations products
    @PostMapping(value = "/superuser/product/paper/add")
    @ResponseBody
    protected ResponseEntity<?> addNewPaperProducts(@Valid @RequestBody final AddPaperForm paperForm,
            final BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(bindingResult.getFieldErrors());
        }

        try {
            System.out.println(paperForm);
            productService.savePaperDetailsForAssociation(paperForm.getAssociationId(), paperForm.getPaperDetails());

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.ok().build();

    }

    // Persist new Data for a associations products
    @PostMapping(value = "/superuser/product/cover/add")
    protected ResponseEntity<?> addNewCovers(@Valid @RequestBody final AddCoverForm coverForm,
            final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(bindingResult.getFieldErrors());
        }

        try {
            System.out.println(coverForm);
            productService.saveCoverDetailsForAssociation(coverForm.getAssociationId(), coverForm.getCoverDetails());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.ok().build();

    }

    /**
     * View for the products of all the association and all the associations
     * 
     * @return modelandview object
     */
    @GetMapping(value = "/superuser/association/all_association_product_view")
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
            logger.error(e.getMessage());
        }

        return modelAndView;

    }

}
