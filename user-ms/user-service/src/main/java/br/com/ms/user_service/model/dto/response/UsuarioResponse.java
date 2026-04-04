package br.com.ms.user_service.model.dto.response;

import br.com.ms.user_service.model.entities.Usuario;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record UsuarioResponse(Long usuarioId, String nome, String uf, BigDecimal renda) {

    public static UsuarioResponse fromEntity(Usuario usuario) {
        return UsuarioResponse.builder()
                .usuarioId(usuario.getId())
                .nome(usuario.getNome())
                .uf(usuario.getEndereco().getUf())
                .renda(usuario.getRenda())
                .build();

    }

    public static List<UsuarioResponse> fromEntities(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioResponse::fromEntity).toList();
    }
}
