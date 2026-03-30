package br.com.ms.geo.exception;

public class CepInvalidoException extends RuntimeException {

    public CepInvalidoException() {
        super("CEP não encontrado");
    }

    public CepInvalidoException(String message) {
        super(message);
    }
}
