package br.com.ms.user_service.model.dto.response;

import br.com.ms.user_service.model.entities.Usuario;
import br.com.ms.user_service.model.enums.UF;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Builder
public record UsuarioResponse(Long usuarioId, String nome, String email, UF uf, BigDecimal renda) {

    public static UsuarioResponse fromEntity(Usuario usuario) {
        UsuarioResponseBuilder usuarioResponseBuilder = UsuarioResponse.builder()
                .usuarioId(usuario.getId())
                .nome(usuario.getNome())
                .uf(usuario.getEndereco().getUf())
                .email(usuario.getEmail())
                .renda(usuario.getRenda());

        return usuarioResponseBuilder.build();

    }

    public static List<UsuarioResponse> fromEntities(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioResponse::fromEntity).toList();
    }
}
