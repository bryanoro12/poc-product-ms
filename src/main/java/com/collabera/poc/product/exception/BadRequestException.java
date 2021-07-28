package com.collabera.poc.product.exception;

import com.collabera.poc.product.common.dto.ErrorMessage;

public class BadRequestException extends RuntimeException {
    private final ErrorMessage errorMessage;

    public BadRequestException(final ErrorMessage errorMessage) {
        super(errorMessage.getDescription());
        this.errorMessage = errorMessage;
    }

    public final ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
