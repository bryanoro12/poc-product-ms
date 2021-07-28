package com.collabera.poc.product.exception;

import com.collabera.poc.product.common.dto.ErrorMessage;

public class ProductNotFoundException extends BadRequestException {
    public ProductNotFoundException(final ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
