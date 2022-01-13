package com.tnt.onlinestore.exceptions.responseEntityExceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException ex) {
        logger.info(ex.getClass().getName());
        String errorMessage = "Entity not found.";
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, errorMessage, ex));
    }

    @ExceptionHandler({NotAuthorisedException.class})
    public ResponseEntity<Object> notAuthorisedException(NotAuthorisedException ex) {
        logger.info(ex.getClass().getName());
        String errorMessage = "You are not authorised to undertake this action.";
        return buildResponseEntity(new ApiError(HttpStatus.FORBIDDEN, errorMessage, ex));
    }

    @ExceptionHandler({InvalidEntryException.class})
    public ResponseEntity<Object> invalidEntryException(InvalidEntryException ex) {
        logger.info(ex.getClass().getName());
        String errorMessage = "Your attempted entry is invalid.";
        return buildResponseEntity(new ApiError(HttpStatus.NOT_ACCEPTABLE, errorMessage, ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info(ex.getClass().getName());
        String errorMessage = "Json request is malformed";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage, ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info(ex.getClass().getName());
        String errorMessage= ex.getMethod() + " method is not supported at this location.";
        return buildResponseEntity(new ApiError(HttpStatus.METHOD_NOT_ALLOWED, errorMessage, ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info(ex.getClass().getName());
        StringBuilder builder = new StringBuilder();
        builder.append("Incorrect media type. The following media types are accepted: ");
        Objects.requireNonNull(ex.getSupportedMediaTypes()).forEach(t -> builder.append(t + " "));
        return buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.toString(), ex));
    }

    //Handle all non-specific errors
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("Error: ", ex);
        String errorMessage = "An unexpected error has occured.";
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, ex));
    }

}
