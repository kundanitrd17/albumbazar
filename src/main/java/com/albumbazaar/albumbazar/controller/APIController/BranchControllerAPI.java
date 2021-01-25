package com.albumbazaar.albumbazar.controller.APIController;

import com.albumbazaar.albumbazar.services.BranchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secured")
public class BranchControllerAPI {
    private final Logger logger = LoggerFactory.getLogger(BranchControllerAPI.class);
    private final BranchService branchService;

    @Autowired
    public BranchControllerAPI(@Qualifier("branchService") final BranchService branchService) {
        this.branchService = branchService;
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

}
