package com.collabera.poc.product.exception;

import com.collabera.poc.product.common.dto.ErrorMessage;

public class UserNotFoundException extends BadRequestException {
    public UserNotFoundException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
