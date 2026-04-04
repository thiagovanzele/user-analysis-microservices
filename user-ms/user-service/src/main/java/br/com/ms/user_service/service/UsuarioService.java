package br.com.ms.user_service.service;

import br.com.ms.user_service.client.GeoClient;
import br.com.ms.user_service.exceptions.ArgumentoInvalidoException;
import br.com.ms.user_service.exceptions.ObjetoNaoEncontradoException;
import br.com.ms.user_service.model.dto.request.UsuarioRequest;
import br.com.ms.user_service.model.dto.response.PageResponse;
import br.com.ms.user_service.model.dto.response.UsuarioResponse;
import br.com.ms.user_service.model.entities.Endereco;
import br.com.ms.user_service.model.entities.Usuario;
import br.com.ms.user_service.model.enums.UF;
import br.com.ms.user_service.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final GeoClient geoClient;

    public UsuarioService(UsuarioRepository usuarioRepository, GeoClient geoClient) {
        this.usuarioRepository = usuarioRepository;
        this.geoClient = geoClient;
    }

    public PageResponse<UsuarioResponse> buscarComFiltros(String uf, BigDecimal renda, Pageable pageable) {
        if (uf != null && renda != null) {
            return buscarUsuarioPorUfERenda(uf, renda, pageable);
        }

        if (uf != null) {
            return buscarUsuariosPorUf(uf, pageable);
        }

        if (renda != null) {
            return buscrUsuarioPorRenda(renda, pageable);
        }

        return buscarTodos(pageable);
    }


    public UsuarioResponse buscarUsuarioPorId(Long usuarioId) {
        Usuario usuario = buscarUsuario(usuarioId);
        return UsuarioResponse.fromEntity(usuario);
    }

    public PageResponse<UsuarioResponse> buscarTodos(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.buscarTodos(pageable);
        Page<UsuarioResponse> responsePage = usuarios.map(UsuarioResponse::fromEntity);

        return PageResponse.fromPage(responsePage);
    }

    public UsuarioResponse atualizarUsuario(UsuarioRequest request, Long usuarioId) {
        Usuario usuario = buscarUsuario(usuarioId);
        Endereco endereco = Endereco.of(geoClient.getViaCep(request.cep()));

        if (!usuario.getNome().equals(request.nome()) && request.nome() != null) {
            usuario.setNome(request.nome());
        }

        if (usuario.getEndereco() != endereco && request.cep() != null) {
            usuario.setEndereco(endereco);
        }

        if (usuario.getRenda().compareTo(request.renda()) != 0) {
            usuario.setRenda(request.renda());
        }

        usuarioRepository.save(usuario);

        return UsuarioResponse.fromEntity(usuario);
    }

    public UsuarioResponse criarUsuario(UsuarioRequest request) {

        Endereco endereco = Endereco.of(geoClient.getViaCep(request.cep()));
        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEndereco(endereco);
        usuario.setRenda(request.renda());

        usuarioRepository.save(usuario);

        return UsuarioResponse.fromEntity(usuario);
    }

    public void seedDB() {
        List<UsuarioRequest> usuarios = List.of(
                new UsuarioRequest("Thiago", "09185030", BigDecimal.valueOf(5000.00)),
                new UsuarioRequest("Tamires", "09061030", BigDecimal.valueOf(3000.00)),
                new UsuarioRequest("Carlos", "01001000", BigDecimal.valueOf(7000.00)),
                new UsuarioRequest("Ana", "20040002", BigDecimal.valueOf(2500.00)),
                new UsuarioRequest("Fernanda", "30140071", BigDecimal.valueOf(4500.00)),
                new UsuarioRequest("Lucas", "40010000", BigDecimal.valueOf(6000.00)),
                new UsuarioRequest("Carlos", "01001000", BigDecimal.valueOf(7000.00)),
                new UsuarioRequest("Ana", "20040002", BigDecimal.valueOf(2500.00)),
                new UsuarioRequest("Fernanda", "30140071", BigDecimal.valueOf(4500.00)),
                new UsuarioRequest("Lucas", "01311000", BigDecimal.valueOf(6000.00)),
                new UsuarioRequest("Mariana", "30130010", BigDecimal.valueOf(3500.00)),
                new UsuarioRequest("João", "80010010", BigDecimal.valueOf(2000.00)),
                new UsuarioRequest("Patricia", "90020120", BigDecimal.valueOf(8000.00)),
                new UsuarioRequest("Rafael", "88015300", BigDecimal.valueOf(5500.00))
        );

        usuarios.forEach(this::criarUsuario);
    }

    private Usuario buscarUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Usuário com id '" + usuarioId + "' não encontrado"));
    }

    private PageResponse<UsuarioResponse> buscarUsuarioPorUfERenda(String uf, BigDecimal renda, Pageable pageable) {
        uf = uf.toUpperCase().trim();
        try {
            UF.valueOf(uf);
        } catch (IllegalArgumentException e) {
            throw new ArgumentoInvalidoException("UF inválido");
        }

        Page<Usuario> usuarios = usuarioRepository.buscarUsuarioPorUfERenda(uf, renda, pageable);
        Page<UsuarioResponse> responsePage =
                usuarios.map(UsuarioResponse::fromEntity);

        return PageResponse.fromPage(responsePage);
    }

    private PageResponse<UsuarioResponse> buscrUsuarioPorRenda(BigDecimal renda, Pageable pageable) {
        Page<UsuarioResponse> responsePage = usuarioRepository.buscarUsuarioPorRenda(renda, pageable)
                .map(UsuarioResponse::fromEntity);

        return PageResponse.fromPage(responsePage);

    }

    private PageResponse<UsuarioResponse> buscarUsuariosPorUf(String uf, Pageable pageable) {
        uf = uf.toUpperCase().trim();
        try {
            UF.valueOf(uf);
        } catch (IllegalArgumentException e) {
            throw new ArgumentoInvalidoException("UF inválido");
        }

        Page<Usuario> usuarios = usuarioRepository.buscarUsuariosPorUf(uf, pageable);
        Page<UsuarioResponse> responsePage =
                usuarios.map(UsuarioResponse::fromEntity);
        return PageResponse.fromPage(responsePage);
    }

}
