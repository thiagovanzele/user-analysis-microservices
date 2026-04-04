package br.com.ms.user_service.exceptions;

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

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<StandardError> objetoNaoEncontradoExceptionHandler(ObjetoNaoEncontradoException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = buildStandardError(ex.getMessage(), status.value(), request);

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(ArgumentoInvalidoException.class)
    public ResponseEntity<StandardError> argumentoInvalidoExcpetionHandler(ArgumentoInvalidoException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = buildStandardError(ex.getMessage(), status.value(), request);

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardValidationError> methodNotValidHandler(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for (final FieldError errorField : ex.getBindingResult().getFieldErrors()) {
            errors.add(errorField.getField() + ": " + errorField.getDefaultMessage());
        }
        StandardValidationError standardValidationError = new StandardValidationError();
        standardValidationError.setMessages(errors);
        standardValidationError.setPath(request.getRequestURI());
        standardValidationError.setStatus(status.value());

        return ResponseEntity.status(status).body(standardValidationError);

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
