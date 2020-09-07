package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {
}
