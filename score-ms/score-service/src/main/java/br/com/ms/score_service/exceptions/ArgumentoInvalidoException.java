package br.com.ms.score_service.exceptions;

public class ArgumentoInvalidoException extends RuntimeException {
    public ArgumentoInvalidoException(String msg) {
        super(msg);
    }
}
