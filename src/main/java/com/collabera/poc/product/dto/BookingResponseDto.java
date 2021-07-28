package com.collabera.poc.product.dto;

import com.collabera.poc.product.entity.Booking;
import lombok.Data;

@Data
public class BookingResponseDto {
    private String referenceNumber;
    private String status;
    private String message;

    public BookingResponseDto(final Booking booking) {
        this.referenceNumber = booking.getReferenceNumber();
        this.status = booking.getStatus();
        this.message = booking.getMessage();
    }
}
