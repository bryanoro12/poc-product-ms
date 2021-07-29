package com.collabera.poc.product.exception;

import com.collabera.poc.product.common.dto.ErrorMessage;

import java.io.Serializable;

public class BadRequestException extends RuntimeException implements Serializable {
    private final ErrorMessage errorMessage;

    public BadRequestException(final ErrorMessage errorMessage) {
        super(errorMessage.getDescription());
        this.errorMessage = errorMessage;
    }

    public final ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
