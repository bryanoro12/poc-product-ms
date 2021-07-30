package com.collabera.poc.product.service.impl;

import com.collabera.poc.product.common.dto.ActivityLog;
import com.collabera.poc.product.common.dto.RequestHeaders;
import com.collabera.poc.product.dto.BookingAcceptRequestDto;
import com.collabera.poc.product.dto.BookingRequestDto;
import com.collabera.poc.product.entity.Booking;
import com.collabera.poc.product.entity.Product;
import com.collabera.poc.product.entity.User;
import com.collabera.poc.product.enums.Action;
import com.collabera.poc.product.enums.Status;
import com.collabera.poc.product.exception.BookingNotFoundException;
import com.collabera.poc.product.exception.ProductNotFoundException;
import com.collabera.poc.product.exception.UserNotFoundException;
import com.collabera.poc.product.repository.BookingRepository;
import com.collabera.poc.product.repository.ProductRepository;
import com.collabera.poc.product.repository.UserRepository;
import com.collabera.poc.product.service.ActivityLogService;
import com.collabera.poc.product.service.BookingService;
import com.collabera.poc.product.service.BookingValidationService;
import com.collabera.poc.product.service.KafkaService;
import com.collabera.poc.product.util.DateTimeUtil;
import com.collabera.poc.product.util.ErrorMessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingValidationService bookingValidationService;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ActivityLogService activityLogService;
    private final KafkaService kafkaService;

    /**
     * Create book
     *
     * @param requestHeaders
     * @param bookingRequestDto
     * @return
     */
    @Override
    public Booking book(
        final RequestHeaders requestHeaders,
        final BookingRequestDto bookingRequestDto
    ) {
        log.info("Request: {}", bookingRequestDto.toString());
        bookingValidationService.validateRequestHeader(requestHeaders);
        bookingValidationService.validateRequestBody(bookingRequestDto);

        log.info("Booking Product...");
        final User user = userRepository
            .findByName(bookingRequestDto.getUserName())
            .orElseThrow(() -> new UserNotFoundException(
                ErrorMessageUtil.ERROR_400_B_USERNAME_DOES_NOT_EXIST));

        final Product product = productRepository
            .findByProductCode(bookingRequestDto.getProductCode())
            .orElseThrow(() -> new ProductNotFoundException(
                ErrorMessageUtil.ERROR_400_B_PRODUCT_CODE_DOES_NOT_EXIST));

        final Booking booking = bookingRepository.save(this.convertDtoToBooking(
            requestHeaders,
            bookingRequestDto,
            user,
            product));

        log.info("Booking save successfully.");
        log.info("Booking: {}", booking.toString());

        final String message = activityLogService.convertActivityLogToString(
            ActivityLog.builder()
                .action(Action.BOOK.toLabel())
                .userName(user.getName())
                .productName(product.getName())
                .productCode(product.getProductCode())
                .build());

        log.info("Message {}", message);
        kafkaService.sendMessage(message);
        return booking;
    }

    /**
     * Check status of booking
     *
     * @param referenceNumber
     * @return
     */
    @Override
    public Booking get(final String referenceNumber) {
        return bookingRepository
            .findByReferenceNumber(referenceNumber)
            .orElse(new Booking());
    }

    /**
     * Accept Booking
     *
     * @param referenceNumber
     * @param bookingAcceptRequestDto
     * @return
     */
    @Override
    public Booking accept(
        final String referenceNumber,
        final BookingAcceptRequestDto bookingAcceptRequestDto
    ) {
        log.info("Request: {}", bookingAcceptRequestDto.toString());
        final Booking booking = bookingRepository
            .findByReferenceNumber(referenceNumber)
            .orElseThrow(() -> new BookingNotFoundException(
                ErrorMessageUtil.ERROR_400_B_BOOKING_DOES_NOT_EXISTS));

        bookingValidationService.validateRequestBody(bookingAcceptRequestDto);

        final Status status = Status.valueOf(bookingAcceptRequestDto.getStatus().toUpperCase());
        booking.setStatus(status.toLabel());
        booking.setMessage(status.toMessage());
        booking.setRemarks(bookingAcceptRequestDto.getRemarks());
        bookingRepository.save(booking);

        log.info("Booking accepted successfully.");
        return booking;
    }

    /**
     * Convert BookingDTO to Booking
     *
     * @param requestHeaders
     * @param bookingRequestDto
     * @param user
     * @param product
     * @return
     */
    @Override
    public Booking convertDtoToBooking(
        final RequestHeaders requestHeaders,
        final BookingRequestDto bookingRequestDto,
        final User user,
        final Product product
    ) {
        log.info("Converting DTO to Booking...");
        return Booking.builder()
            .requestId(requestHeaders.getRequestId())
            .user(user)
            .product(product)
            .startDate(LocalDateTime.parse(
                bookingRequestDto.getStartDate(),
                DateTimeFormatter.ofPattern(DateTimeUtil.YYYYMMDDHHMM)))
            .endDate(LocalDateTime.parse(
                bookingRequestDto.getEndDate(),
                DateTimeFormatter.ofPattern(DateTimeUtil.YYYYMMDDHHMM)))
            .referenceNumber(UUID.randomUUID().toString())
            .status(Status.PENDING.toLabel())
            .message(Status.PENDING.toMessage())
            .build();
    }
}
