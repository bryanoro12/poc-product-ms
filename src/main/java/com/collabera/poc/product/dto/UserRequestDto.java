package com.collabera.poc.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
public class UserRequestDto {
    @ApiModelProperty(example = "Bryan")
    private String name;
    @ApiModelProperty(example = "oro.bryan12@gmail.com")
    private String email;
    @ApiModelProperty(example = "Metro Manila")
    private String address;
}
