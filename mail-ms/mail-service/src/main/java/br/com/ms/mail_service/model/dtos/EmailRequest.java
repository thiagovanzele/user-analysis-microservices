package br.com.ms.mail_service.model.dtos;

public record EmailRequest(UsuarioRequest para, String assunto, String mensagem) {
}
