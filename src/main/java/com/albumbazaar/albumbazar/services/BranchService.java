package com.albumbazaar.albumbazar.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.albumbazaar.albumbazar.dao.BranchRepository;
import com.albumbazaar.albumbazar.model.Branch;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("branchService")
public class BranchService {
    final BranchRepository branchRepository;
    public BranchService(final BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }


    public boolean addBranch(/*  Take in the ModelAttributes in order to process studd  */) {
        // create model with the branch details
        final Branch branch = new Branch();
        // validate branch details then save the brnach

        branchRepository.save(branch);

        return true;
    }

    public void deletebranch(final Long id) {

        // check if the branch existes
        branchRepository.deleteById(id);

    }

    public Branch updateBranch(final Branch updatedBranchInfo) {
        // get the branch and validate new info
        final Branch branch = branchRepository.findById(updatedBranchInfo.getId()).get();
        /*
        ... update info
        */
        // save bach
        branchRepository.save(branch);

        return branch;
    }

    public Optional<List<Branch>> getAllBranch() {
        final List<Branch> allBranch = branchRepository.findAll().stream().collect(Collectors.toList());

        return Optional.of(allBranch);
    }

    public Branch getbranch(final Long id) throws NoSuchElementException {
        return branchRepository.findById(id).get();
    }


}
