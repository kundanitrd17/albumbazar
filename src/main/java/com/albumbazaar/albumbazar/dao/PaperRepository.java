package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper, Long> {
}
