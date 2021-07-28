package com.collabera.poc.product.service;

import com.collabera.poc.product.dto.UserRequestDto;

public interface UserValidationService {
    void validateRequestBody(UserRequestDto userRequestDto);
}
