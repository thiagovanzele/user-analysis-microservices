package br.com.ms.score_service.models.dto.response;

import br.com.ms.score_service.models.enums.UF;
import lombok.Builder;

import java.math.BigDecimal;


@Builder
public record UsuarioResponse(Long usuarioId, String nome, String email, UF uf, BigDecimal renda) {

}