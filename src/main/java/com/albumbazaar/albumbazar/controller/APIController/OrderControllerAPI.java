package com.albumbazaar.albumbazar.controller.APIController;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.albumbazaar.albumbazar.dto.ErrorDTO;
import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.principals.CustomerPrincipal;
import com.albumbazaar.albumbazar.services.GoogleDriveService;
import com.albumbazaar.albumbazar.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api")
public class OrderControllerAPI {

    private final Logger logger = LoggerFactory.getLogger(OrderControllerAPI.class);

    private final OrderService orderService;
    private final GoogleDriveService googleDriveService;

    @Autowired(required = true)
    protected OrderControllerAPI(@Qualifier("orderService") final OrderService orderService,
            @Qualifier("googleDriveService") final GoogleDriveService googleDriveService) {
        this.orderService = orderService;
        this.googleDriveService = googleDriveService;

    }

    /**
     * Change the order status given order id, and new order status.
     * 
     * @return ResponseEntity
     */
    @PostMapping(value = "secured/order/status")
    public ResponseEntity<?> changeStatusOfTheOrder(@RequestBody final OrderDetailDTO order) {

        try {

            // Get customer care's id from the security context object
            final Long customerCareId = 1l;
            orderService.changeOrderStatus(order.getId(), customerCareId, order.getStatus());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error(e.getMessage());

        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping(value = "secured/order/info")
    public ResponseEntity<?> updateOrderInfo(@RequestBody @Valid final OrderDetailDTO orderDetailInfo) {

        try {
            return ResponseEntity.ok().body(orderService.updateOrderInfo(orderDetailInfo.getId(), orderDetailInfo));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            final ErrorDTO error = new ErrorDTO();

            error.setMessage("Unable to update orderInfo");
            return ResponseEntity.badRequest().body(error);

        }

    }

    @GetMapping(value = "secured/order/{order_id}/create-folder")
    public ResponseEntity<?> createGoogleDriveFolderAndSaveId(
            @PathVariable(name = "order_id") OrderDetail orderDetail) {

        System.out.println(orderDetail);
        try {
            googleDriveService.createFolderAndMakePublic("folderName", "chuhiyadotcom@gmail.com", orderDetail);
            final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof CustomerPrincipal) {
                final CustomerPrincipal customerPrincipal = (CustomerPrincipal) principal;

                googleDriveService.createFolderAndMakePublic("folderName", customerPrincipal.getUsername(),
                        orderDetail);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());

            final ErrorDTO error = new ErrorDTO();
            error.setMessage("unable to create folder... try again");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        return ResponseEntity.ok().body("body");

    }

    @PostMapping(value = "secured/order/{order_id}/photos", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> uploadPhotos(@PathVariable(name = "order_id") final OrderDetail orderInfo,
            @RequestPart("files") List<MultipartFile> files) {

        try {
            files.forEach(e -> logger.info(e.getOriginalFilename()));

            googleDriveService.uploadToGoogleDrive(files, orderInfo.getPhotoFolderGoogleDriveId(),
                    "chuhiyadotcom@gmail.com");
            final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof CustomerPrincipal) {
                final CustomerPrincipal customerPrincipal = (CustomerPrincipal) principal;
                googleDriveService.uploadToGoogleDrive(files, orderInfo.getPhotoFolderGoogleDriveId(),
                        customerPrincipal.getUsername());
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("Unable to process request");
        } catch (Exception e) {
            logger.error(e.getMessage());

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body("body");
    }

}
