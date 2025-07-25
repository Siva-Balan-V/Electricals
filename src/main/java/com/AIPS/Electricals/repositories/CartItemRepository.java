package com.AIPS.Electricals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.AIPS.Electricals.models.CartItem;

@Repository  // <--- this is optional but helps sometimes
public interface CartItemRepository extends JpaRepository<CartItem, Long> {}
