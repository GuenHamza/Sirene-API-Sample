package com.izicap.sirene.api.common.exception;

import com.izicap.sirene.api.common.ErrorDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@RestController
public class IzicapExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({IzicapException.class})
    public ResponseEntity<ErrorDto> handleIzicapException(final IzicapException iziEx) {
        log.error("{} [{}]", iziEx.getClass().getName(), iziEx.getMessage());
        log.error(iziEx.getMessage(), iziEx);
        final ErrorDto errorDetails = new ErrorDto(iziEx.getMessage(), iziEx.getHttpStatus().value());
        errorDetails.setAdditionalData(iziEx.getCause() != null ? iziEx.getCause().getLocalizedMessage() : iziEx.getLocalizedMessage());
        return new ResponseEntity<>(errorDetails, iziEx.getHttpStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllExceptions(final Exception ex, WebRequest request) {
        log.error("{} detected [{}]", ex.getClass().getName(), ex.getMessage());
        log.error(ex.getMessage(), ex);
        final ErrorDto errorDetails = new ErrorDto(ExceptionKeys.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.setAdditionalData(ex.getCause() != null ? ex.getCause().getLocalizedMessage() : ex.getLocalizedMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
