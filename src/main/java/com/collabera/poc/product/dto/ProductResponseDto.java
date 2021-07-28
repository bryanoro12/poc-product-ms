package com.collabera.poc.product.dto;

import com.collabera.poc.product.entity.Product;
import lombok.*;

@Data
public class ProductResponseDto {
    private String name;
    private String productCode;
    private String price;

    public ProductResponseDto(final Product product) {
        this.name = product.getName();
        this.productCode = product.getProductCode();
        this.price = String.valueOf(product.getPrice());
    }
}
