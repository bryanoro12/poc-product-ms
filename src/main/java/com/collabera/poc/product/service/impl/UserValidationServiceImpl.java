package com.collabera.poc.product.service.impl;

import com.collabera.poc.product.dto.UserRequestDto;
import com.collabera.poc.product.service.UserValidationService;
import com.collabera.poc.product.util.ErrorMessageUtil;
import com.collabera.poc.product.util.FieldValidationUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class UserValidationServiceImpl implements UserValidationService {

    /**
     * Validate User Request Body
     *
     * @param userRequestDto
     */
    @Override
    public void validateRequestBody(final UserRequestDto userRequestDto) {
        log.info("Validating user request body...");
        final List<List<Object>> fields = new ArrayList<>();
        fields.add(FieldValidationUtil.field(
            userRequestDto.getName(),
            ErrorMessageUtil.ERROR_400_U_INVALID_NAME));

        fields.add(FieldValidationUtil.field(
            userRequestDto.getEmail(),
            ErrorMessageUtil.ERROR_400_U_INVALID_EMAIL));

        fields.add(FieldValidationUtil.field(
            userRequestDto.getAddress(),
            ErrorMessageUtil.ERROR_400_U_INVALID_ADDRESS));

        FieldValidationUtil.validateEmailFormat(
            userRequestDto.getEmail(),
            ErrorMessageUtil.ERROR_400_U_INVALID_EMAIL_FORMAT);

        FieldValidationUtil.validateFields(fields);
    }
}
