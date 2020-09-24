package com.albumbazaar.albumbazar.dao;

import java.util.List;

import com.albumbazaar.albumbazar.model.Paper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaperRepository extends JpaRepository<Paper, Long> {

    @Query(value = "SELECT * FROM paper WHERE association_id = ?1", nativeQuery = true)
    List<Paper> findAllByAssociationId(Long id);

}
