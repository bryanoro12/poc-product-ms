package com.collabera.poc.product.service;

import com.collabera.poc.product.common.dto.RequestHeaders;
import com.collabera.poc.product.dto.BookingAcceptRequestDto;
import com.collabera.poc.product.dto.BookingRequestDto;
import com.collabera.poc.product.entity.Booking;
import com.collabera.poc.product.entity.Product;
import com.collabera.poc.product.entity.User;

public interface BookingService {
    Booking book(
        RequestHeaders requestHeaders,
        BookingRequestDto bookingRequestDto);

    Booking get(String referer);

    Booking accept(
        String referenceNumber,
        BookingAcceptRequestDto bookingAcceptRequestDto);

    Booking convertDtoToBooking(
        RequestHeaders requestHeaders,
        BookingRequestDto bookingRequestDto,
        User user,
        Product product);
}
