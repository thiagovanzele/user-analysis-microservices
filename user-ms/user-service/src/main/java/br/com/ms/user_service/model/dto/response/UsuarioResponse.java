package br.com.ms.user_service.model.dto.response;

import br.com.ms.user_service.model.entities.Usuario;
import lombok.Builder;

@Builder
public record UsuarioResponse(Long usuarioId, String nome) {

    public static UsuarioResponse fromEntity(Usuario usuario) {
        return UsuarioResponse.builder()
                .usuarioId(usuario.getId())
                .nome(usuario.getNome())
                .build();

    }
}
