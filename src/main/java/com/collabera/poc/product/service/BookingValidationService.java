package com.collabera.poc.product.service;

import com.collabera.poc.product.common.dto.RequestHeaders;
import com.collabera.poc.product.dto.BookingAcceptRequestDto;
import com.collabera.poc.product.dto.BookingRequestDto;

public interface BookingValidationService {
    void validateRequestBody(BookingRequestDto bookingRequestDto);

    void validateRequestBody(BookingAcceptRequestDto bookingAcceptRequestDto);

    void validateRequestHeader(RequestHeaders requestHeaders);
}
