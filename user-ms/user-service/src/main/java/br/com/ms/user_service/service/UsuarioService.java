package br.com.ms.user_service.service;

import br.com.ms.user_service.client.GeoClient;
import br.com.ms.user_service.model.dto.request.UsuarioRequest;
import br.com.ms.user_service.model.dto.response.UsuarioResponse;
import br.com.ms.user_service.model.entities.Endereco;
import br.com.ms.user_service.model.entities.Usuario;
import br.com.ms.user_service.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final GeoClient geoClient;

    public UsuarioService(UsuarioRepository usuarioRepository, GeoClient geoClient) {
        this.usuarioRepository = usuarioRepository;
        this.geoClient = geoClient;
    }

    public UsuarioResponse criarUsuario(UsuarioRequest request) {

        Endereco endereco = Endereco.of(geoClient.getViaCep(request.cep()));
        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEndereco(endereco);

        usuarioRepository.save(usuario);

        return UsuarioResponse.fromEntity(usuario);
    }
}
