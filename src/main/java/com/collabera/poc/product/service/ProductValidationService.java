package com.collabera.poc.product.service;

import com.collabera.poc.product.common.dto.RequestHeaders;
import com.collabera.poc.product.dto.ProductRequestDto;

public interface ProductValidationService {
    void validateRequestBody(ProductRequestDto productRequestDto);

    void validateRequestHeader(RequestHeaders requestHeaders);
}
