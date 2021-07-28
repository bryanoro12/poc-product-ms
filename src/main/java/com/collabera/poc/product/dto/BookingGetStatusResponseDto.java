package com.collabera.poc.product.dto;

import com.collabera.poc.product.entity.Booking;
import com.collabera.poc.product.entity.Product;
import com.collabera.poc.product.entity.User;
import lombok.Data;

@Data
public class BookingGetStatusResponseDto {
    private String referenceNumber;
    private String status;
    private String message;
    private String remarks;
    private User user;
    private Product product;
    private String startDate;
    private String endDate;
    private String transactionDate;

    public BookingGetStatusResponseDto(final Booking booking) {
        this.referenceNumber = booking.getReferenceNumber();
        this.status = booking.getStatus();
        this.message = booking.getMessage();
        this.remarks = booking.getRemarks();
        this.user = booking.getUser();
        this.product = booking.getProduct();
        this.startDate = booking.getStartDate();
        this.endDate = booking.getEndDate();
        this.transactionDate = String.valueOf(booking.getTransactionDate());
    }
}
