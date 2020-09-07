package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
