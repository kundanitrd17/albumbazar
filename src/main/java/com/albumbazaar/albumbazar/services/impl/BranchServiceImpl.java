package com.albumbazaar.albumbazar.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.albumbazaar.albumbazar.dao.Address1Repository;
import com.albumbazaar.albumbazar.dao.Address2Repository;
import com.albumbazaar.albumbazar.dao.BranchRepository;
import com.albumbazaar.albumbazar.dao.EmployeeRepository;
import com.albumbazaar.albumbazar.dto.BranchDTO;
import com.albumbazaar.albumbazar.form.BasicBranchInfoForm;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.model.Address1;
import com.albumbazaar.albumbazar.model.Address2;
import com.albumbazaar.albumbazar.model.Branch;
import com.albumbazaar.albumbazar.model.Employee;
import com.albumbazaar.albumbazar.services.BranchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("branchService")
public class BranchServiceImpl implements BranchService {

    private final Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);

    final BranchRepository branchRepository;
    final Address1Repository address1Repository;
    final Address2Repository address2Repository;
    final EmployeeRepository employeeRepository;

    public BranchServiceImpl(final BranchRepository branchRepository, final Address1Repository address1Repository,
            final EmployeeRepository employeeRepository, final Address2Repository address2Repository) {
        this.branchRepository = branchRepository;
        this.address1Repository = address1Repository;
        this.address2Repository = address2Repository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean addBranch(final BasicBranchInfoForm branchInfo, final LocationForm locationDetails) {
        try {
            // working on the address2 creation (pin address)
            final Address2 address2 = new Address2(locationDetails); // creating the address2 model
            address2Repository.save(address2); // saving the model

            // Working on the address1 creation (street address)
            final Address1 address1 = new Address1(locationDetails); // creating the address1 model
            address1.setAddress2(address2); // mapping the address2 to address1
            address1Repository.save(address1); // saving address1

            // Setting the admin for this branch
            Employee admin;
            try {
                admin = employeeRepository.findByEmail(branchInfo.getAdmin());
            } catch (NoSuchElementException e) {
                admin = null;
            }

            // Working on the branch creation
            final Branch branch = new Branch(branchInfo); // creating the branch model
            branch.setAdmin(admin); // mapping to the admin employee
            branch.setAddress(address1); // mapping the address column to the address1
            branchRepository.save(branch); // saving the branch

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    @Transactional
    public Branch updateBranch(final BranchDTO updatedBranchInfo) {

        final Branch branch = branchRepository.findById(updatedBranchInfo.getId()).orElseThrow();

        if (updatedBranchInfo.getName() != null && !updatedBranchInfo.getName().isBlank()) {
            branch.setName(updatedBranchInfo.getName());
        }

        /**
         * Change other parameters for updates
         */

        return branch;
    }

    @Override
    public Optional<List<Branch>> getAllBranch() {
        final List<Branch> allBranch = branchRepository.findAll().stream().collect(Collectors.toList());

        return Optional.of(allBranch);
    }

    @Override
    public Branch getbranch(final Long branchId) throws NoSuchElementException {
        return branchRepository.findById(branchId).get();
    }

    @Override
    public Branch deletebranch(final Long branchId) {

        return updateBranchStatus(branchId, false);
    }

    @Override
    public Branch restoreBranch(Long branchId) {
        return updateBranchStatus(branchId, true);
    }

    private final Branch updateBranchStatus(final Long branchId, final Boolean status) {
        try {
            final Branch branch = branchRepository.findById(branchId).orElseThrow();
            branch.setActive(status);
            return branchRepository.save(branch);

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Unable to make change to the branch");
        }
    }

}
