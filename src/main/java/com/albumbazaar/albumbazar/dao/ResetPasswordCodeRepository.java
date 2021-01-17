package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.ResetPasswordCode;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetPasswordCodeRepository extends JpaRepository<ResetPasswordCode, String> {

}
