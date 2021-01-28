package com.albumbazaar.albumbazar.controller.APIController;

import com.albumbazaar.albumbazar.principals.EmployeePrincipal;
import com.albumbazaar.albumbazar.services.BranchService;
import com.albumbazaar.albumbazar.services.GoogleDriveService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secured")
public class BranchControllerAPI {
    private final Logger logger = LoggerFactory.getLogger(BranchControllerAPI.class);
    private final BranchService branchService;
    private final GoogleDriveService googleDriveService;

    @Autowired
    public BranchControllerAPI(@Qualifier("googleDriveService") final GoogleDriveService googleDriveService,
            @Qualifier("branchService") final BranchService branchService) {
        this.branchService = branchService;
        this.googleDriveService = googleDriveService;
    }

    @GetMapping(value = "/branch/{branch_id}")
    public ResponseEntity<?> getBranchInfo(@PathVariable("branch_id") final Long branchId) {
        try {
            return ResponseEntity.ok(branchService.getbranch(branchId));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = { "/branch/is-google-auth-allowed" })
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

}
