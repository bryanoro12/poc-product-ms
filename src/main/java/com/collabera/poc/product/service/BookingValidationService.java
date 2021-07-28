package com.collabera.poc.product.service;

import com.collabera.poc.product.common.dto.RequestHeaders;
import com.collabera.poc.product.dto.BookingRequestDto;

public interface BookingValidationService {
    void validateRequestBody(BookingRequestDto bookingRequestDto);

    void validateRequestHeader(RequestHeaders requestHeaders);
}
