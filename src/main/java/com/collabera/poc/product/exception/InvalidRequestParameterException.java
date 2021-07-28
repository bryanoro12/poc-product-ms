package com.collabera.poc.product.exception;

import com.collabera.poc.product.common.dto.ErrorMessage;

public class InvalidRequestParameterException extends BadRequestException {
    public InvalidRequestParameterException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
