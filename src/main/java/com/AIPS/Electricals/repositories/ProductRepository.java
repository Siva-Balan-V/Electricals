package com.AIPS.Electricals.repositories;

import com.AIPS.Electricals.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
