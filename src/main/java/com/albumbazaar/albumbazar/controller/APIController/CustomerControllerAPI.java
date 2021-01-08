package com.albumbazaar.albumbazar.controller.APIController;

import javax.validation.Valid;

import com.albumbazaar.albumbazar.dto.CustomerDTO;
import com.albumbazaar.albumbazar.dto.ErrorDTO;
import com.albumbazaar.albumbazar.model.Customer;
import com.albumbazaar.albumbazar.principals.CustomerPrincipal;
import com.albumbazaar.albumbazar.services.CustomerService;
import com.albumbazaar.albumbazar.services.GoogleDriveService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/secured/customer")
public class CustomerControllerAPI {

    private final Logger logger = LoggerFactory.getLogger(CustomerControllerAPI.class);

    private final CustomerService customerService;
    private final GoogleDriveService googleDriveService;

    protected CustomerControllerAPI(@Qualifier("customerService") final CustomerService customerService,
            @Qualifier("googleDriveService") final GoogleDriveService googleDriveService) {
        this.customerService = customerService;
        this.googleDriveService = googleDriveService;
    }

    @PutMapping("info")
    public ResponseEntity<?> emp(@RequestBody @Valid CustomerDTO customerInfo) {

        try {
            final Customer customer = customerService.updateCustomerInfo(customerInfo);

            return ResponseEntity.ok().body(customer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            final ErrorDTO error = new ErrorDTO();
            error.setMessage("Unable to update info");
            return ResponseEntity.badRequest().body(error);
        }

    }

    @GetMapping(value = "wallet/{id}")
    public ResponseEntity<?> getWalletInfo(@PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok().body(customerService.getWalletAmount(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping(value = "/address")
    protected ResponseEntity<?> deleteAddressOfACustomer(@RequestBody final Long addressId) {

        final CustomerPrincipal principal = (CustomerPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        customerService.deleteAddress(principal.getId(), addressId);

        return ResponseEntity.ok().body("body");
    }

    @GetMapping(value = { "/is-google-auth-allowed" })
    public ResponseEntity<?> isSignedInToGoogleAccount() {

        if (SecurityContextHolder.getContext().getAuthentication() != null) {

            final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof CustomerPrincipal) {

                final CustomerPrincipal customerPrincipal = (CustomerPrincipal) principal;
                Boolean isLogged = googleDriveService.isAuthenticatedToGoogle(customerPrincipal.getUsername());
                if (isLogged == true) {
                    return ResponseEntity.ok().build();
                }
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
