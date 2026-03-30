package br.com.ms.user_service.controller;

import br.com.ms.user_service.model.dto.request.UsuarioRequest;
import br.com.ms.user_service.model.dto.response.UsuarioResponse;
import br.com.ms.user_service.model.entities.Usuario;
import br.com.ms.user_service.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/criar")
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody UsuarioRequest usuarioRequest){
        UsuarioResponse usuario = usuarioService.criarUsuario(usuarioRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
}
