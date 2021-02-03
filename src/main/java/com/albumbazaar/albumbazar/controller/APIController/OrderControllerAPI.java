package com.albumbazaar.albumbazar.controller.APIController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.albumbazaar.albumbazar.Mapper.AddressMapper;
import com.albumbazaar.albumbazar.dto.ErrorDTO;
import com.albumbazaar.albumbazar.dto.OrderDetailDTO;
import com.albumbazaar.albumbazar.dto.ProductDetailDTO;
import com.albumbazaar.albumbazar.dto.SheetDetailDTO;
import com.albumbazaar.albumbazar.form.order.OrderDetailFormDTO;
import com.albumbazaar.albumbazar.model.AddressEntity;
import com.albumbazaar.albumbazar.model.OrderDetail;
import com.albumbazaar.albumbazar.model.OrderDetailStatus;
import com.albumbazaar.albumbazar.principals.CustomerPrincipal;
import com.albumbazaar.albumbazar.principals.EmployeePrincipal;
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
    AddressMapper addressMapper;

    @Autowired(required = true)
    protected OrderControllerAPI(@Qualifier("orderService") final OrderService orderService,
            @Qualifier("googleDriveService") final GoogleDriveService googleDriveService) {
        this.orderService = orderService;
        this.googleDriveService = googleDriveService;

    }

    @GetMapping(value = "/secured/order/customer/{customer_id}")
    public ResponseEntity<?> getCustomerOrders(@PathVariable("customer_id") final Long customerId) {

        try {
            return ResponseEntity.ok().body(orderService.getOrdersOfCustomer(customerId));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();

    }

    @GetMapping(value = "/secured/order/branch/{branch_id}")
    public ResponseEntity<?> getBranchOrder(@PathVariable("branch_id") final Long branchId) {

        try {
            return ResponseEntity.ok().body(orderService.getOrdersOfBranch(branchId));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/secured/order/{order-id}")
    public ResponseEntity<?> orderInfo(@PathVariable("order-id") final Long orderId) {

        try {
            System.out.println(orderId);
            final OrderDetail orderDetail = orderService.getOrder(orderId);
            orderDetail.getCover();

            return ResponseEntity.ok(orderDetail);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.notFound().build();
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
            final EmployeePrincipal employeePrincipal = (EmployeePrincipal) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            orderService.changeOrderStatus(order.getId(), employeePrincipal.getId(), order.getStatus());
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

        try {
            String USER_IDENTIFICATION_KEY = new String();
            final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof CustomerPrincipal) {
                final CustomerPrincipal customerPrincipal = (CustomerPrincipal) principal;
                USER_IDENTIFICATION_KEY = customerPrincipal.getUsername();
            } else if (principal instanceof EmployeePrincipal) {
                final EmployeePrincipal employeePrincipal = (EmployeePrincipal) principal;
                USER_IDENTIFICATION_KEY = employeePrincipal.getUsername();
            } else {
                throw new RuntimeException();
            }
            googleDriveService.createFolderAndMakePublic("folderName", USER_IDENTIFICATION_KEY, orderDetail);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        final ErrorDTO error = new ErrorDTO();
        error.setMessage("unable to create folder... try again");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

    @PostMapping(value = "secured/order/{order_id}/photos", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> uploadPhotos(@PathVariable(name = "order_id") final OrderDetail orderInfo,
            @RequestPart("files") List<MultipartFile> files) {

        try {

            files.forEach(e -> logger.info(e.getOriginalFilename()));

            String USER_IDENTIFICATION_KEY = new String();
            final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof CustomerPrincipal) {
                final CustomerPrincipal customerPrincipal = (CustomerPrincipal) principal;
                USER_IDENTIFICATION_KEY = customerPrincipal.getUsername();
            } else if (principal instanceof EmployeePrincipal) {
                final EmployeePrincipal employeePrincipal = (EmployeePrincipal) principal;
                USER_IDENTIFICATION_KEY = employeePrincipal.getUsername();
            } else {
                throw new RuntimeException();
            }

            googleDriveService.uploadToGoogleDrive(files, orderInfo.getPhotoFolderGoogleDriveId(),
                    USER_IDENTIFICATION_KEY);

            return ResponseEntity.ok().build();

        } catch (IOException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("Unable to Upload Files...!");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/secured/order/{order_id}/sheet/details")
    public ResponseEntity<?> getSheetDetail(@PathVariable("order_id") final Long orderId) {

        try {
            final List<SheetDetailDTO> sheetDetailDTOs = orderService.getSheetDetails(orderId);

            HashMap<String, Object> data = new HashMap<>();
            data.put("data", sheetDetailDTOs);

            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/secured/order/{order_id}/product/details")
    public ResponseEntity<?> getProductDetail(@PathVariable("order_id") final Long orderId) {

        try {
            final ProductDetailDTO productDetailDTO = orderService.getProductInfo(orderId);

            return ResponseEntity.ok().body(productDetailDTO);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/secured/order/{order_id}/delivery/address")
    public ResponseEntity<?> getOrderDeliveryAddress(@PathVariable("order_id") final Long orderId) {

        try {
            final AddressEntity addressEntity = orderService.getDeliveryAddress(orderId);
            return ResponseEntity.ok().body(addressEntity);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/secured/order/forward/association")
    public ResponseEntity<?> forwardOrderToAssociation(@RequestBody final long orderId) {

        try {
            orderService.forwardToAssociation(orderId);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/secured/order/create")
    public ResponseEntity<?> createNewOrder(OrderDetailFormDTO orderDetailFormDTO) {

        logger.info(orderDetailFormDTO.toString());
        try {

            final EmployeePrincipal employeePrincipal = (EmployeePrincipal) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            final OrderDetail createdOrder = orderService.createOrderByBranchOrAdmin(orderDetailFormDTO,
                    employeePrincipal.getId());
            return ResponseEntity.ok().body(createdOrder);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().body("Unable to create Order... Please check details");

    }

    @PutMapping(value = "/secured/delivery/order/{order_id}/under-process")
    public ResponseEntity<?> orderUnderProcessing(@PathVariable("order_id") final Long orderId) {

        try {

            orderService.changeDeliveryStatus(orderId, OrderDetailStatus.DELIVERY_UNDER_PROCESS);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/secured/delivery/order/{order_id}/delivered")
    public ResponseEntity<?> orderDelivered(@PathVariable("order_id") final Long orderId) {

        try {

            orderService.changeDeliveryStatus(orderId, OrderDetailStatus.COMPLETED);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

}
