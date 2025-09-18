package org.servicekecils.catalogservice.repository;

import org.servicekecils.catalogservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findBySku(String sku);

    Page<Product> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);
}
