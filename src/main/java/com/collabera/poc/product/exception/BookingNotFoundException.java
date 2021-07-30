package com.collabera.poc.product.exception;

import com.collabera.poc.product.common.dto.ErrorMessage;

public class BookingNotFoundException extends BadRequestException {
    public BookingNotFoundException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
