package com.albumbazaar.albumbazar.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.albumbazaar.albumbazar.dto.AddressDTO;
import com.albumbazaar.albumbazar.dto.BranchDTO;
import com.albumbazaar.albumbazar.model.Branch;

public interface BranchService {

    boolean addBranch(BranchDTO branchInfo, final AddressDTO locationDetails);

    Branch deletebranch(final Long id);

    Branch updateBranch(final BranchDTO updatedBranchInfo);

    Optional<List<Branch>> getAllBranch();

    Branch getbranch(final Long id);

    Branch restoreBranch(Long branchId);

    List<BranchDTO> getAllActiveBranchName();

    void updateAddressInfo(AddressDTO addressDTO, Long branchId);

    Branch getBranchWithCode(String branchCode) throws NoSuchElementException;

}
