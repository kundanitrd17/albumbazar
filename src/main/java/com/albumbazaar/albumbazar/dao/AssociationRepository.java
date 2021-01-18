package com.albumbazaar.albumbazar.dao;

import java.util.List;
import java.util.Optional;

import com.albumbazaar.albumbazar.model.Association;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociationRepository extends JpaRepository<Association, Long> {

    List<Association> findByActive(Boolean active);

    Optional<Association> findByEmail(String username);
}
