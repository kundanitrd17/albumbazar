package com.albumbazaar.albumbazar.dao;

import java.util.List;
import java.util.Optional;

import com.albumbazaar.albumbazar.dto.BranchDTO;
import com.albumbazaar.albumbazar.model.Branch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    @Query(value = "SELECT branch.id, branch.name FROM branch WHERE active = true", nativeQuery = true)
    Optional<List<Object[]>> getNameOfAllActiveBranches();

}
