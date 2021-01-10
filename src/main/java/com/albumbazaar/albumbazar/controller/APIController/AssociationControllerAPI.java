
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

        String image = associationService.getAssociation(1l).getProfilePhoto();

        if (image != null && !image.isBlank()) {
            String photo = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile", image)
                    .build().toUri().toString();

            return ResponseEntity.ok(photo);
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("/secured/association/order/accept")
    public ResponseEntity<?> acceptOrderByAssociation(@RequestBody Long orderId) {

        System.err.println(orderId);

        try {
            associationService.acceptOrder(orderId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/secured/association/order/completed")
    public ResponseEntity<?> workDoneByAssociation(@RequestBody Long orderId) {

        System.err.println(orderId);

        try {
            associationService.setOrderCompleted(orderId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/secured/association/order/deliver")
    public ResponseEntity<?> deliverByAssociation(@RequestBody Long orderId) {

        System.err.println(orderId);

        try {
            associationService.processForDelivery(orderId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

}