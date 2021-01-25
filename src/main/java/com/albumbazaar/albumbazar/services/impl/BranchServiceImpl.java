package com.albumbazaar.albumbazar.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.dao.BranchRepository;
import com.albumbazaar.albumbazar.dao.EmployeeRepository;
import com.albumbazaar.albumbazar.dto.BranchDTO;
import com.albumbazaar.albumbazar.form.BasicBranchInfoForm;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.model.Branch;
import com.albumbazaar.albumbazar.model.Employee;
import com.albumbazaar.albumbazar.services.BranchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("branchService")
public class BranchServiceImpl implements BranchService {

    private final Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);

    final BranchRepository branchRepository;
    final EmployeeRepository employeeRepository;

    @Autowired
    public BranchServiceImpl(final BranchRepository branchRepository, final EmployeeRepository employeeRepository) {

        this.branchRepository = branchRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public boolean addBranch(final BasicBranchInfoForm branchInfo, final LocationForm locationDetails) {
        try {

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
            // branch.setAddress(address1); // mapping the address column to the address1
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
            branch.setName(updatedBranchInfo.getName().trim());
        }

        if (updatedBranchInfo.getContactNo() != null && !updatedBranchInfo.getContactNo().isBlank()) {
            branch.setContactNo(updatedBranchInfo.getContactNo().trim());
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

    @Override
    public List<BranchDTO> getAllActiveBranchName() {
        final List<BranchDTO> listOfBranchDTO = new ArrayList<>();

        branchRepository.getNameOfAllActiveBranches().ifPresentOrElse(branches -> branches.stream().forEach(branch -> {
            try {
                BranchDTO branchDTO = new BranchDTO();
                branchDTO.setId(Long.parseLong(branch[0].toString()));
                branchDTO.setName(branch[1].toString());

                listOfBranchDTO.add(branchDTO);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }), () -> {
            throw new NoSuchElementException("No value");
        });

        return listOfBranchDTO;

    }

}
