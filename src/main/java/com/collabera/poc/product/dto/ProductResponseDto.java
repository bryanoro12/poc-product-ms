package com.collabera.poc.product.dto;

import com.collabera.poc.product.entity.Product;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private String name;
    private String description;
    private String price;

    public ProductResponseDto(final Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = String.valueOf(product.getPrice());
    }
}
