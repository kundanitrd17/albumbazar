package com.albumbazaar.albumbazar.controller;

import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "association")
public final class AssociationController {

    private Logger logger = LoggerFactory.getLogger(AssociationController.class);

    private AssociationService associationService;
    private ProductService productService;

    @Autowired
    public AssociationController(@Qualifier("associationService") final AssociationService associationService,
            @Qualifier("productService") final ProductService productService) {
        this.associationService = associationService;
        this.productService = productService;
    }

    @PostMapping("/dp/change")
    public RedirectView changeProfilePhoto(@RequestParam("photo") final MultipartFile photoFile,
            final RedirectAttributes redirectAttributes) {

        final RedirectView redirectView = new RedirectView("/association");

        try {
            // Get the association id from security context
            Long id = 1l;
            associationService.changeProfilePhoto(photoFile, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            redirectAttributes.addAttribute("error", "Unable to change photo");
        }

        return redirectView;

    }

}
