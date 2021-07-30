package com.collabera.poc.product.controller;

import com.collabera.poc.product.common.dto.RequestHeaders;
import com.collabera.poc.product.dto.BookingAcceptRequestDto;
import com.collabera.poc.product.dto.BookingGetStatusResponseDto;
import com.collabera.poc.product.dto.BookingRequestDto;
import com.collabera.poc.product.dto.BookingResponseDto;
import com.collabera.poc.product.entity.Booking;
import com.collabera.poc.product.service.BookingService;
import com.collabera.poc.product.util.ResponseCodesUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Book Product")
    @ApiResponses(value = {
        @ApiResponse(code = ResponseCodesUtil.BAD_REQUEST, message = "Invalid request parameter"),
        @ApiResponse(code = ResponseCodesUtil.CREATED, message = "Successful book product")})
    public ResponseEntity<BookingResponseDto> bookProduct(
        @ApiParam(
            required = true,
            example = "0de4be40-48a2-4f45-89b9-9f8a6a05791a")
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
    @ApiOperation(value = "Get Booking Status")
    @ApiResponses(value = {
        @ApiResponse(code = ResponseCodesUtil.BAD_REQUEST, message = "Invalid request parameter"),
        @ApiResponse(code = ResponseCodesUtil.CREATED, message = "Successful get booking status")})
    public ResponseEntity<BookingGetStatusResponseDto> getBookingStatus(
        @ApiParam(
            required = true,
            example = "0de4be40-48a2-4f45-89b9-9f8a6a05791a")
        @PathVariable final String referenceNumber
    ) {
        return new ResponseEntity<>(
            new BookingGetStatusResponseDto(bookingService.get(referenceNumber)),
            HttpStatus.OK);
    }

    /**
     * Accept Booking
     *
     * @param referenceNumber
     * @param bookingAcceptRequestDto
     * @return
     */
    @PutMapping("/booking/accept/{referenceNumber}")
    @ApiOperation(value = "Accept Booking")
    @ApiResponses(value = {
        @ApiResponse(code = ResponseCodesUtil.BAD_REQUEST, message = "Invalid request parameter"),
        @ApiResponse(code = ResponseCodesUtil.CREATED, message = "Successful accept booking")})
    public ResponseEntity<Booking> acceptBooking(
        @ApiParam(
            required = true,
            example = "0de4be40-48a2-4f45-89b9-9f8a6a05791a")
        @PathVariable("referenceNumber") final String referenceNumber,
        @RequestBody final BookingAcceptRequestDto bookingAcceptRequestDto
    ) {
        return new ResponseEntity<>(
            bookingService.accept(referenceNumber, bookingAcceptRequestDto),
            HttpStatus.OK);
    }
}
