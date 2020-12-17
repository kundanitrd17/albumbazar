package com.albumbazaar.albumbazar.services;

import java.util.List;
import java.util.Optional;

import com.albumbazaar.albumbazar.dto.BranchDTO;
import com.albumbazaar.albumbazar.form.BasicBranchInfoForm;
import com.albumbazaar.albumbazar.form.LocationForm;
import com.albumbazaar.albumbazar.model.Branch;

public interface BranchService {

    boolean addBranch(final BasicBranchInfoForm branchInfo, final LocationForm locationDetails);

    Branch deletebranch(final Long id);

    Branch updateBranch(final BranchDTO updatedBranchInfo);

    Optional<List<Branch>> getAllBranch();

    Branch getbranch(final Long id);

    Branch restoreBranch(Long branchId);

}
