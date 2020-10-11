package com.albumbazaar.albumbazar.dao;

import java.util.List;

import com.albumbazaar.albumbazar.model.Association;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociationRepository extends JpaRepository<Association, Long> {

    List<Association> findByActive(Boolean active);
}
