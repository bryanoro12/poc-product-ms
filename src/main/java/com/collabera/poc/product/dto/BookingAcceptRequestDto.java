package com.collabera.poc.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BookingAcceptRequestDto {
    @ApiModelProperty(example = "Completed")
    private String status;
    @ApiModelProperty(example = "Thank you for booking this product")
    private String remarks;
}
