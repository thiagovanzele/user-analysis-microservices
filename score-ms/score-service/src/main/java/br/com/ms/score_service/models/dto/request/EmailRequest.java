package br.com.ms.score_service.models.dto.request;

import java.util.List;

public record EmailRequest(UsuarioRequest para, String assunto, String mensagem) {
}
