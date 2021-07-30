package com.collabera.poc.product.dto;

import com.collabera.poc.product.entity.Booking;
import com.collabera.poc.product.entity.Product;
import com.collabera.poc.product.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BookingGetStatusResponseDto {
    @ApiModelProperty(example = "0de4be40-48a2-4f45-89b9-9f8a6a05791a")
    private String referenceNumber;
    @ApiModelProperty(example = "Completed")
    private String status;
    @ApiModelProperty(example = "Your booking is completed")
    private String message;
    @ApiModelProperty(example = "Thank you for booking this product")
    private String remarks;
    private User user;
    private Product product;
    @ApiModelProperty(example = "2020-01-19T08:00")
    private String startDate;
    @ApiModelProperty(example = "2020-01-21T08:00")
    private String endDate;
    @ApiModelProperty(example = "2021-07-27T00:10:34.308847")
    private String transactionDate;

    public BookingGetStatusResponseDto(final Booking booking) {
        this.referenceNumber = booking.getReferenceNumber();
        this.status = booking.getStatus();
        this.message = booking.getMessage();
        this.remarks = booking.getRemarks();
        this.user = booking.getUser();
        this.product = booking.getProduct();
        this.startDate = String.valueOf(booking.getStartDate());
        this.endDate = String.valueOf(booking.getEndDate());
        this.transactionDate = String.valueOf(booking.getTransactionDate());
    }
}
