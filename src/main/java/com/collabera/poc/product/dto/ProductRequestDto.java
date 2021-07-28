package com.collabera.poc.product.dto;

import lombok.*;

@Data
public class ProductRequestDto {
    private String name;
    private String description;
    private String price;
    private String createdBy;
    private String updatedBy;
}
