package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.Branch;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    
}
