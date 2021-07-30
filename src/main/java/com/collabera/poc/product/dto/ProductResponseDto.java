package com.collabera.poc.product.dto;

import com.collabera.poc.product.entity.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
public class ProductResponseDto {
    @ApiModelProperty(example = "HONDA_CIVIC")
    private String name;
    @ApiModelProperty(example = "20210727000951HONDA_CIVIC")
    private String productCode;
    @ApiModelProperty(example = "100")
    private String price;

    public ProductResponseDto(final Product product) {
        this.name = product.getName();
        this.productCode = product.getProductCode();
        this.price = String.valueOf(product.getPrice());
    }
}
