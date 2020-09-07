package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.DeletedInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedInventoryRepository extends JpaRepository<DeletedInventory, Long> {
}
