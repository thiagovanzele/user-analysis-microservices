package br.com.ms.mail_service.model.dtos;

import tools.jackson.core.SerializableString;

import java.util.List;

public record EmailRequest(UsuarioRequest para, String assunto, String mensagem) {
}
