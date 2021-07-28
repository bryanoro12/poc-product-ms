package com.collabera.poc.product.controller;

import com.collabera.poc.product.common.dto.RequestHeaders;
import com.collabera.poc.product.dto.BookingGetStatusResponseDto;
import com.collabera.poc.product.dto.BookingRequestDto;
import com.collabera.poc.product.dto.BookingResponseDto;
import com.collabera.poc.product.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    /**
     * Book Product Controller
     *
     * @param requestId
     * @param bookingRequestDto
     * @return
     */
    @PostMapping("/booking")
    public ResponseEntity<BookingResponseDto> bookProduct(
        @RequestHeader final String requestId,
        @RequestBody final BookingRequestDto bookingRequestDto
    ) {
        return new ResponseEntity<>(
            new BookingResponseDto(bookingService.book(
                new RequestHeaders(requestId),
                bookingRequestDto)),
            HttpStatus.CREATED);
    }

    /**
     * Get Booking Status
     *
     * @param referenceNumber
     * @return
     */
    @GetMapping("/booking/{referenceNumber}")
    public ResponseEntity<BookingGetStatusResponseDto> getBookingStatus(
        @PathVariable final String referenceNumber
    ) {
        return new ResponseEntity<>(
            new BookingGetStatusResponseDto(bookingService.get(referenceNumber)),
            HttpStatus.OK);
    }
}
