package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.Superuser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperuserRepository extends JpaRepository<Superuser, Long>{

    Superuser findByEmail(String email);
    
}