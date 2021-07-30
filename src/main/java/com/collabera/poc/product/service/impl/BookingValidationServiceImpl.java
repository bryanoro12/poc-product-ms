package com.collabera.poc.product.service.impl;

import com.collabera.poc.product.common.dto.RequestHeaders;
import com.collabera.poc.product.dto.BookingAcceptRequestDto;
import com.collabera.poc.product.dto.BookingRequestDto;
import com.collabera.poc.product.enums.Status;
import com.collabera.poc.product.exception.InvalidRequestParameterException;
import com.collabera.poc.product.repository.BookingRepository;
import com.collabera.poc.product.service.BookingValidationService;
import com.collabera.poc.product.util.ErrorMessageUtil;
import com.collabera.poc.product.util.FieldValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookingValidationServiceImpl implements BookingValidationService {
    private final BookingRepository bookingRepository;

    /**
     * Validate Booking Reqyest Body
     *
     * @param bookingRequestDto
     */
    @Override
    public void validateRequestBody(final BookingRequestDto bookingRequestDto) {
        log.info("Validating booking request body...");
        final List<List<Object>> fields = new ArrayList<>();
        fields.add(FieldValidationUtil.field(
            bookingRequestDto.getUserName(),
            ErrorMessageUtil.ERROR_400_B_INVALID_USERNAME));

        fields.add(FieldValidationUtil.field(
            bookingRequestDto.getProductCode(),
            ErrorMessageUtil.ERROR_400_B_INVALID_PRODUCT_CODE));

        fields.add(FieldValidationUtil.field(
            bookingRequestDto.getStartDate(),
            ErrorMessageUtil.ERROR_400_B_INVALID_STARTDATE));

        fields.add(FieldValidationUtil.field(
            bookingRequestDto.getEndDate(),
            ErrorMessageUtil.ERROR_400_B_INVALID_ENDDATE));

        FieldValidationUtil.validateDateFormat(
            bookingRequestDto.getStartDate(),
            ErrorMessageUtil.ERROR_400_B_INVALID_STARTDATE_FORMAT);

        FieldValidationUtil.validateDateFormat(
            bookingRequestDto.getEndDate(),
            ErrorMessageUtil.ERROR_400_B_INVALID_ENDATE_FORMAT);

        FieldValidationUtil.validateFields(fields);
    }

    /**
     * Validate Accept Booking Request Body
     * @param bookingAcceptRequestDto
     */
    @Override
    public void validateRequestBody(final BookingAcceptRequestDto bookingAcceptRequestDto) {
        log.info("Validating booking accept request body...");
        FieldValidationUtil.validateField(
            bookingAcceptRequestDto.getStatus(),
            ErrorMessageUtil.ERROR_400_B_INVALID_STATUS);

        if (!EnumUtils.isValidEnumIgnoreCase(Status.class, bookingAcceptRequestDto.getStatus())) {
            throw new InvalidRequestParameterException(ErrorMessageUtil.ERROR_400_B_INVALID_STATUS);
        }
    }

    /**
     * Validate Booking Request Header
     *
     * @param requestHeaders
     */
    @Override
    public void validateRequestHeader(final RequestHeaders requestHeaders) {
        log.info("Validating booking request header...");
        FieldValidationUtil.validateField(
            requestHeaders.getRequestId(),
            ErrorMessageUtil.ERROR_400_B_INVALID_REQUEST_ID);

        bookingRepository
            .findByRequestId(requestHeaders.getRequestId())
            .ifPresent(product -> {
                throw new InvalidRequestParameterException(
                    ErrorMessageUtil.ERROR_400_B_DUPLICATE_REQUEST_ID);
            });
    }
}
