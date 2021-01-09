package com.albumbazaar.albumbazar.controller.APIController;

import com.albumbazaar.albumbazar.dto.ErrorDTO;
import com.albumbazaar.albumbazar.principals.CustomerPrincipal;
import com.albumbazaar.albumbazar.principals.EmployeePrincipal;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.GoogleDriveService;
import com.albumbazaar.albumbazar.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/admin")
public class AdminControllerAPI {

    private final Logger logger = LoggerFactory.getLogger(AdminControllerAPI.class);

    private final CustomerService customerService;
    private final GoogleDriveService googleDriveService;
    private final OrderService orderService;

    @Autowired(required = true)
    protected AdminControllerAPI(@Qualifier("customerService") final CustomerService customerService,
            @Qualifier("googleDriveService") final GoogleDriveService googleDriveService,
            @Qualifier("orderService") final OrderService orderService) {
        this.customerService = customerService;
        this.googleDriveService = googleDriveService;
        this.orderService = orderService;
    }

    @DeleteMapping(value = "customer-delete")
    public ResponseEntity<?> deactivateCustomer(@RequestBody Long customerId) {

        try {
            customerService.deleteCustomer(customerId);
            return ResponseEntity.ok().body("done deleting");
        } catch (Exception e) {
            logger.error(e.getMessage());

            final ErrorDTO error = new ErrorDTO();
            error.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }

    }

    @PutMapping(value = "customer-restore")
    public ResponseEntity<?> activateCustomer(@RequestBody Long customerId) {
        try {

            customerService.restoreCustomer(customerId);
            return ResponseEntity.ok().body("done restoring");
        } catch (Exception e) {
            logger.error(e.getMessage());
            final ErrorDTO error = new ErrorDTO();
            error.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }

    }

    @GetMapping(value = { "/is-google-auth-allowed" })
    public ResponseEntity<?> isSignedInToGoogleAccount() {

        try {
            if (SecurityContextHolder.getContext().getAuthentication() != null) {

                final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (principal instanceof EmployeePrincipal) {

                    final EmployeePrincipal employeePrincipal = (EmployeePrincipal) principal;
                    Boolean isLogged = googleDriveService.isAuthenticatedToGoogle(employeePrincipal.getUsername());
                    if (isLogged == true) {
                        return ResponseEntity.ok().build();
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping(value = "/order/{order_id}/description")
    public ResponseEntity<?> updateOrderDescription(@PathVariable("order_id") final Long orderId,
            @RequestBody final String description) {

        try {
            logger.info(description);
            orderService.changeOrderDescription(orderId, description);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error(e.getMessage());

        }

        return ResponseEntity.badRequest().build();
    }

}
