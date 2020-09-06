package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.Location;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    
}
