package br.com.ms.user_service.exceptions;

public class ArgumentoInvalidoException extends RuntimeException {
    public ArgumentoInvalidoException(String message) {
        super(message);
    }
}
