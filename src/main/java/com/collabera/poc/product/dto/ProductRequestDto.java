package com.collabera.poc.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
public class ProductRequestDto {
    @ApiModelProperty(example = "HONDA_CIVIC")
    private String name;
    @ApiModelProperty(example = "RS Emblem and LED Headlights with LED Daytime Running Lights")
    private String description;
    @ApiModelProperty(example = "100")
    private String price;
    @ApiModelProperty(example = "Bryan")
    private String createdBy;
}
