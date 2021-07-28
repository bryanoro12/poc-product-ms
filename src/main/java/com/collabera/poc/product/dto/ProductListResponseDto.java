package com.collabera.poc.product.dto;

import com.collabera.poc.product.entity.Product;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductListResponseDto {
    private List<ProductResponseDto> products;

    public ProductListResponseDto(final List<Product> products) {
        this.products = products
            .stream()
            .map(ProductResponseDto::new)
            .collect(Collectors.toList());
    }
}
