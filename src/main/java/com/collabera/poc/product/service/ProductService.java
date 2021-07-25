package com.collabera.poc.product.service;

import com.collabera.poc.product.dto.ProductRequestDto;
import com.collabera.poc.product.entity.Product;

import java.util.List;

public interface ProductService {
    Product add(ProductRequestDto productRequestDto);

    List<Product> getAll();

    Product convertDtoToProduct(ProductRequestDto productRequestDto);
}
