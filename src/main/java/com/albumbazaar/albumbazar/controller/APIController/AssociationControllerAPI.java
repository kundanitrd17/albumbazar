package com.albumbazaar.albumbazar.controller.APIController;

import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class AssociationControllerAPI {

    private Logger logger = LoggerFactory.getLogger(AssociationControllerAPI.class);

    private AssociationService associationService;
    private ProductService productService;

    @Autowired
    public AssociationControllerAPI(@Qualifier("associationService") final AssociationService associationService,
            @Qualifier("productService") final ProductService productService) {
        this.associationService = associationService;
        this.productService = productService;
    }

}
