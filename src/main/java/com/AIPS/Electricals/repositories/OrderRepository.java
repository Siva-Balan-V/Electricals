package com.AIPS.Electricals.repositories;

import com.AIPS.Electricals.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
