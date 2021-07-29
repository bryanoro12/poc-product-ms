package com.collabera.poc.product.service.impl;

import com.collabera.poc.product.common.dto.RequestHeaders;
import com.collabera.poc.product.dto.ProductRequestDto;
import com.collabera.poc.product.exception.InvalidRequestParameterException;
import com.collabera.poc.product.repository.ProductRepository;
import com.collabera.poc.product.service.ProductValidationService;
import com.collabera.poc.product.util.ErrorMessageUtil;
import com.collabera.poc.product.util.FieldValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductValidationServiceImpl implements ProductValidationService {
    private final ProductRepository productRepository;

    /**
     * Validate Product Request Body
     *
     * @param productRequestDto
     */
    @Override
    public void validateRequestBody(final ProductRequestDto productRequestDto) {
        log.info("Validating product request body...");
        final List<List<Object>> fields = new ArrayList<>();
        fields.add(FieldValidationUtil.field(
            productRequestDto.getName(),
            ErrorMessageUtil.ERROR_400_P_INVALID_NAME));

        fields.add(FieldValidationUtil.field(
            productRequestDto.getDescription(),
            ErrorMessageUtil.ERROR_400_P_INVALID_DESCRIPTION));

        fields.add(FieldValidationUtil.field(
            productRequestDto.getPrice(),
            ErrorMessageUtil.ERROR_400_P_INVALID_PRICE));

        fields.add(FieldValidationUtil.field(
            productRequestDto.getCreatedBy(),
            ErrorMessageUtil.ERROR_400_P_INVALID_CREATED_BY));

        FieldValidationUtil.validatePriceFormat(
            productRequestDto.getPrice(),
            ErrorMessageUtil.ERROR_400_P_INVALID_PRICE_FORMAT);

        productRepository
            .findByName(productRequestDto.getName())
            .ifPresent(product -> {
                throw new InvalidRequestParameterException(
                    ErrorMessageUtil.ERROR_400_P_DUPLICATE_PRODUCT_NAME);
            });

        FieldValidationUtil.validateFields(fields);
    }

    /**
     * Validate Product Request Header
     *
     * @param requestHeaders
     */
    @Override
    public void validateRequestHeader(final RequestHeaders requestHeaders) {
        log.info("Validating product request header...");
        FieldValidationUtil.validateField(
            requestHeaders.getRequestId(),
            ErrorMessageUtil.ERROR_400_P_INVALID_REQUEST_ID);

        productRepository
            .findByRequestId(requestHeaders.getRequestId())
            .ifPresent(product -> {
                throw new InvalidRequestParameterException(
                    ErrorMessageUtil.ERROR_400_P_DUPLICATE_REQUEST_ID);
            });
    }
}
