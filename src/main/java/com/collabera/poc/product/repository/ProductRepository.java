package com.collabera.poc.product.repository;

import com.collabera.poc.product.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();

    Optional<Product> findByProductCode(String productCode);

    Optional<Product> findByRequestId(String requestId);

    Optional<Product> findByName(String name);
}
