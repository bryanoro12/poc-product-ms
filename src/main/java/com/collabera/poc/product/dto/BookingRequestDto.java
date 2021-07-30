package com.collabera.poc.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BookingRequestDto {
    @ApiModelProperty(example = "Bryan")
    private String userName;
    @ApiModelProperty(example = "20210727000951HONDA_CIVIC")
    private String productCode;
    @ApiModelProperty(example = "2020-01-19")
    private String startDate;
    @ApiModelProperty(example = "2020-01-21")
    private String endDate;
}
