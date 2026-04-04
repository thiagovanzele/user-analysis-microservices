package br.com.ms.user_service.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException {

    public ObjetoNaoEncontradoException() {
        super("Objeto não encontrado");
    }

    public ObjetoNaoEncontradoException(String msg) {
        super(msg);
    }
}
