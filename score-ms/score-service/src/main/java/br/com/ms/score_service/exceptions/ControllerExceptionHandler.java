package br.com.ms.score_service.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(ArgumentoInvalidoException.class)
    public ResponseEntity<StandardError> argumentoInvalidoExcpetionHandler(ArgumentoInvalidoException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = buildStandardError(ex.getMessage(), status.value(), request);

        return ResponseEntity.status(status).body(error);

    }


    private StandardError buildStandardError(String message, int status, HttpServletRequest request) {
        StandardError error = new StandardError();
        error.setMessage(message);
        error.setStatus(status);
        error.setPath(request.getRequestURI());
        error.setTimestamp(Instant.now());

        return error;
    }
}
