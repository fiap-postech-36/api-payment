package br.com.kitchen.application.infra;

import br.com.kitchen.application.exception.MercadoPagoIntegrationException;
import br.com.kitchen.application.exception.ResourceNotFound;
import br.com.kitchen.domain.core.exception.CoreExceptionNegocial;
import br.com.kitchen.domain.core.exception.CoreExceptionRuntime;
import br.com.kitchen.infra.exception.OrderStatusControlNotFoundException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
        IllegalArgumentException.class,
        IllegalStateException.class,
        ValidationException.class,
        CoreExceptionNegocial.class,
        CoreExceptionRuntime.class})
    public ResponseEntity<Object> handleBadRequest(
            Exception ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = Collections.singletonList(ex.getMessage());


        if (ex instanceof MethodArgumentNotValidException bindException) {
            errors = bindException.getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        }

        return handleExceptionInternal(ex, new ApiErrorMessage(status, errors),
            new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {
        RuntimeException.class
    })
    public ResponseEntity<Object> handleInternal(
            Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        log.info(ex.getMessage());
        return createResponseEntity( new ApiErrorMessage(status, List.of(ex.getMessage())),
            new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {
            OrderStatusControlNotFoundException.class
    })
    public ResponseEntity<Object> orderStatusControlNotFoundException(
            Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        log.info(ex.getMessage());
        return createResponseEntity( new ApiErrorMessage(status, List.of(ex.getMessage())),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {
            MercadoPagoIntegrationException.class
    })
    public ResponseEntity<Object> mercadoPagoIntegrationException(
            Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        log.info(ex.getMessage());
        return createResponseEntity( new ApiErrorMessage(status, List.of(ex.getMessage())),
                new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @ExceptionHandler(value = {
        NoSuchElementException.class,
        ResourceNotFound.class
    })
    public ResponseEntity<Object> handleNotFound(
            Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return createResponseEntity( new ApiErrorMessage(status, List.of(ex.getMessage())),
            new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


}