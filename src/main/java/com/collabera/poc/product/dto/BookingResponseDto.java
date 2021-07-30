package com.collabera.poc.product.dto;

import com.collabera.poc.product.entity.Booking;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BookingResponseDto {
    @ApiModelProperty(example = "0de4be40-48a2-4f45-89b9-9f8a6a05791a")
    private String referenceNumber;
    @ApiModelProperty(example = "Completed")
    private String status;
    @ApiModelProperty(example = "Thank you for booking this product")
    private String message;

    public BookingResponseDto(final Booking booking) {
        this.referenceNumber = booking.getReferenceNumber();
        this.status = booking.getStatus();
        this.message = booking.getMessage();
    }
}
