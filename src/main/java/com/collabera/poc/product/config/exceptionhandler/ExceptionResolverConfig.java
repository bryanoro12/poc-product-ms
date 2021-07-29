package com.collabera.poc.product.config.exceptionhandler;

import com.collabera.poc.product.common.dto.ErrorMessage;
import com.collabera.poc.product.exception.BadRequestException;
import com.collabera.poc.product.exception.InvalidRequestParameterException;
import com.collabera.poc.product.exception.ProductNotFoundException;
import com.collabera.poc.product.exception.UserNotFoundException;
import com.collabera.poc.product.util.ErrorMessageUtil;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

@Log4j2
@RestControllerAdvice
public class ExceptionResolverConfig {

    /**
     * Bad request handling
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler({
        InvalidRequestParameterException.class,
        ProductNotFoundException.class,
        UserNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @SneakyThrows
    public ResponseEntity<ErrorMessage> badRequestHandler(
        final BadRequestException exception,
        final WebRequest request,
        final HandlerMethod handlerMethod
    ) {
        log.info(request.toString());
        log.info(exception.getClass().getCanonicalName());
        log.error("[BadRequestException] Handler: {} ErrorCode: {}, Message: {}",
            handlerMethod.getMethod().getDeclaringClass(),
            exception.getErrorMessage().getErrorCode(),
            exception.getErrorMessage().getDescription());

        return generateResponseEntity(
            exception.getErrorMessage(),
            HttpStatus.BAD_REQUEST);
    }

    /**
     * Not found handler
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> pathNotFoundHandler(
        final NoHandlerFoundException exception,
        final WebRequest request
    ) {
        log.info(request.toString());
        log.info(exception.getClass().getCanonicalName());
        log.error("[NotFound] Message: {}", exception.getMessage());

        return generateResponseEntity(
            ErrorMessageUtil.ERROR_404_G_NOT_FOUND,
            HttpStatus.NOT_FOUND);
    }

    /**
     * Method not allowed handling
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorMessage> methodNotAllowedHandler(
        final Exception exception,
        final WebRequest request
    ) {
        log.info(request.toString());
        log.info(exception.getClass().getCanonicalName());
        log.error("[MethodNotAllowed] Message: {}",
            exception.getMessage());

        return generateResponseEntity(
            ErrorMessageUtil.ERROR_405_G_METHOD_NOT_ALLOWED,
            HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Global Handler Exception
     *
     * @param exception
     * @param request
     * @param handlerMethod
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(
        final Exception exception,
        final WebRequest request,
        final HandlerMethod handlerMethod
    ) {
        log.info(request.toString());
        log.info(exception.getClass().getCanonicalName());
        log.error("[RunTimeException] Handler: {} Message: {}",
            handlerMethod.getMethod().getDeclaringClass(),
            exception.getMessage());

        return generateResponseEntity(
            ErrorMessageUtil.ERROR_500_G_INTERNAL_SERVER_ERROR,
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessage> generateResponseEntity(
        final ErrorMessage errorMessage,
        final HttpStatus httpStatus
    ) {
        final ErrorMessage response = ErrorMessage.builder()
            .errorCode(errorMessage.getErrorCode())
            .description(errorMessage.getDescription())
            .build();

        return ResponseEntity
            .status(httpStatus)
            .body(response);
    }
}
