package io.vendhan.social.handler;

import io.vendhan.social.exception.InvalidInputException;
import io.vendhan.social.model.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final static Logger log =
            LogManager.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = { InvalidInputException.class })
    protected ResponseEntity<Object> handleNotFound(
            InvalidInputException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.addError(ex.getMessage());
        log.error(ex);
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { ConstraintViolationException.class})
    protected ResponseEntity<Object> handleUnprocessableEntity(
            ConstraintViolationException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        Set<ConstraintViolation<?>> constraintViolations =
                ex.getConstraintViolations();

        errorResponse.addAll(constraintViolations.stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .collect(Collectors.toList()));
        log.error(ex);
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.addError(ex.getMessage());
        log.error(ex);
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
