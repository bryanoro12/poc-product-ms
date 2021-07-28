package com.collabera.poc.product.dto;

import lombok.Data;

@Data
public class BookingRequestDto {
    private String userName;
    private String productCode;
    private String startDate;
    private String endDate;
}
