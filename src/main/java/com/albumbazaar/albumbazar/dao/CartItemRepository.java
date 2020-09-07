package com.albumbazaar.albumbazar.dao;

import com.albumbazaar.albumbazar.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
