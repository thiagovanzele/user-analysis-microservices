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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final GeoClient geoClient;

    public UsuarioService(UsuarioRepository usuarioRepository, GeoClient geoClient) {
        this.usuarioRepository = usuarioRepository;
        this.geoClient = geoClient;
    }

    public PageResponse<UsuarioResponse> buscarComFiltros(String uf, BigDecimal renda, Pageable pageable) {
        List<UF> ufsForSearch;
        if (uf == null) {
            ufsForSearch = gerarUfs();
        } else {
            ufsForSearch = List.of(normalizeUf(uf));
        }

        if (uf != null && renda != null) {
            return buscarUsuarioPorUfERenda(ufsForSearch, renda, pageable);
        }

        if (uf != null) {
            return buscarUsuariosPorUf(ufsForSearch, pageable);
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
        usuario.setEmail(request.email());

        if (request.renda() == null) {
            usuario.setRenda(BigDecimal.valueOf(0));
        } else {
            usuario.setRenda(request.renda());
        }

        usuarioRepository.save(usuario);

        return UsuarioResponse.fromEntity(usuario);
    }

    public void seedDB() {
        List<UsuarioRequest> usuarios = List.of(
                new UsuarioRequest("Thiago", "09185030", "thiago.testemicroservicos@proton.me", BigDecimal.valueOf(9000.00)),
                new UsuarioRequest("Tamires", "09061030", "tamires@gmail.com", BigDecimal.valueOf(3000.00)),
                new UsuarioRequest("Carlos", "01001000", "carlos1@gmail.com", BigDecimal.valueOf(7000.00)),
                new UsuarioRequest("Ana", "20040002", "ana1@gmail.com", BigDecimal.valueOf(2500.00)),
                new UsuarioRequest("Fernanda", "30140071", "fernanda1@gmail.com", BigDecimal.valueOf(4500.00)),
                new UsuarioRequest("Lucas", "40010000", "lucas1@gmail.com", BigDecimal.valueOf(6000.00)),
                new UsuarioRequest("Carlos", "01001000", "carlos2@gmail.com", BigDecimal.valueOf(7000.00)),
                new UsuarioRequest("Ana", "20040002", "ana2@gmail.com", BigDecimal.valueOf(2500.00)),
                new UsuarioRequest("Fernanda", "30140071", "fernanda2@gmail.com", BigDecimal.valueOf(4500.00)),
                new UsuarioRequest("Lucas", "01311000", "lucas2@gmail.com", BigDecimal.valueOf(6000.00)),
                new UsuarioRequest("Mariana", "30130010", "mariana@gmail.com", BigDecimal.valueOf(3500.00)),
                new UsuarioRequest("João", "80010010", "joao@gmail.com", BigDecimal.valueOf(2000.00)),
                new UsuarioRequest("Patricia", "90020120", "patricia@gmail.com", BigDecimal.valueOf(8000.00)),
                new UsuarioRequest("Rafael", "88015300", "rafael@gmail.com", BigDecimal.valueOf(5500.00))
        );

        usuarios.forEach(this::criarUsuario);
    }

    private Usuario buscarUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Usuário com id '" + usuarioId + "' não encontrado"));
    }

    private PageResponse<UsuarioResponse> buscarUsuarioPorUfERenda(List<UF> ufForSearch, BigDecimal renda, Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.buscarUsuarioPorUfERenda(ufForSearch, renda, pageable);
        Page<UsuarioResponse> responsePage =
                usuarios.map(UsuarioResponse::fromEntity);

        return PageResponse.fromPage(responsePage);
    }

    private PageResponse<UsuarioResponse> buscrUsuarioPorRenda(BigDecimal renda, Pageable pageable) {
        Page<UsuarioResponse> responsePage = usuarioRepository.buscarUsuarioPorRenda(renda, pageable)
                .map(UsuarioResponse::fromEntity);

        return PageResponse.fromPage(responsePage);

    }

    private PageResponse<UsuarioResponse> buscarUsuariosPorUf(List<UF> ufForSearch, Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.buscarUsuariosPorUf(ufForSearch, pageable);
        Page<UsuarioResponse> responsePage =
                usuarios.map(UsuarioResponse::fromEntity);
        return PageResponse.fromPage(responsePage);
    }

    private List<UF> gerarUfs() {
        return List.of(UF.values());
    }

    private UF normalizeUf(String ufFromApi) {
        return UF.fromApi(ufFromApi)
                .orElseThrow(() -> new ArgumentoInvalidoException("UF inválidO"));

    }

}
