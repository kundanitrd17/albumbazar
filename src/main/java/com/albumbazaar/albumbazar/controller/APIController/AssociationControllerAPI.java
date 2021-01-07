
package com.albumbazaar.albumbazar.controller.APIController;

import com.albumbazaar.albumbazar.controller.FileUploadController;
import com.albumbazaar.albumbazar.services.AssociationService;
import com.albumbazaar.albumbazar.services.ProductService;
import com.albumbazaar.albumbazar.services.storage.StorageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
@RequestMapping("/api")
public class AssociationControllerAPI {

    private Logger logger = LoggerFactory.getLogger(AssociationControllerAPI.class);

    private AssociationService associationService;
    private ProductService productService;
    private final StorageService imageStorageService;

    @Autowired
    public AssociationControllerAPI(@Qualifier("associationService") final AssociationService associationService,
            @Qualifier("productService") final ProductService productService,
            @Qualifier("imageStorageService") final StorageService imageStorageService) {
        this.associationService = associationService;
        this.imageStorageService = imageStorageService;
        this.productService = productService;
    }

    @GetMapping("secured/association/dp")
    public ResponseEntity<?> associationProfilePhoto() {
        String photo = MvcUriComponentsBuilder
                .fromMethodName(FileUploadController.class, "serveFile", imageStorageService
                        .load(associationService.getAssociation(1l).getProfilePhoto()).getFileName().toString())
                .build().toUri().toString();

        return ResponseEntity.ok(photo);

    }

}