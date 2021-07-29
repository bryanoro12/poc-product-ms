package com.collabera.poc.product.util;

import com.collabera.poc.product.common.dto.ErrorMessage;
import com.collabera.poc.product.exception.InvalidRequestParameterException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public final class FieldValidationUtil {
    public static final Integer FIELD_NAME = 0;
    public static final Integer ERROR_MESSAGE = 1;

    private FieldValidationUtil() {
    }

    /**
     * Validate Request fields
     *
     * @param fields
     */
    public static void validateFields(final List<List<Object>> fields) {
        fields.forEach(field -> {
            if (Optional.ofNullable(field.get(FIELD_NAME)).isEmpty()
                || String.valueOf(field.get(FIELD_NAME)).isBlank()
            ) {
                throw new InvalidRequestParameterException((ErrorMessage) field.get(ERROR_MESSAGE));
            }
        });
    }

    /**
     * Validate Field
     *
     * @param field
     * @param errorMessage
     */
    public static void validateField(
        final Object field,
        final ErrorMessage errorMessage
    ) {
        if (Optional.ofNullable(field).isEmpty()
            || String.valueOf(field).isBlank()
        ) {
            throw new InvalidRequestParameterException(errorMessage);
        }
    }

    /**
     * Add object
     *
     * @param object
     */
    public static List<Object> field(
        final Object object,
        final ErrorMessage errorMessage
    ) {
        final List<Object> key = new ArrayList<>();
        key.add(object);
        key.add(errorMessage);

        return key;
    }

    /**
     * Validate Email Format
     *
     * @param email
     * @param errorMessage
     */
    public static void validateEmailFormat(
        final String email,
        final ErrorMessage errorMessage
    ) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new InvalidRequestParameterException(errorMessage);
        }
    }

    /**
     * Validate Price Format
     *
     * @param price
     * @param errorMessage
     */
    public static void validatePriceFormat(final String price, final ErrorMessage errorMessage) {
        if (!DoubleValidator.getInstance().isValid(price)) {
            throw new InvalidRequestParameterException(errorMessage);
        }
    }

    /**
     * Validate Date Format
     * @param date
     * @param errorMessage
     */
    public static void validateDateFormat(final String date, final ErrorMessage errorMessage) {
        boolean isValid = false;
        try {
            LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DateTimeUtil.YYYYMMDDHHMM));
        } catch (final DateTimeParseException exception) {
            isValid = true;
            log.error("[DateFormatInvalid] : {}", exception.getMessage());
        }

        if (isValid) {
            throw new InvalidRequestParameterException(errorMessage);
        }
    }
}
