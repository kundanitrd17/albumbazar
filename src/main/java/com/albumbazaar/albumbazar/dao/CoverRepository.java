package com.albumbazaar.albumbazar.dao;

import java.util.List;

import com.albumbazaar.albumbazar.model.Association;
import com.albumbazaar.albumbazar.model.Cover;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CoverRepository extends JpaRepository<Cover, Long> {

    @Query(value = "SELECT * FROM cover WHERE association_id = ?1", nativeQuery = true)
    List<Cover> findAllByAssociationId(Long id);

    List<Cover> findAllByAssociationAndActive(Association association, boolean active);

}
