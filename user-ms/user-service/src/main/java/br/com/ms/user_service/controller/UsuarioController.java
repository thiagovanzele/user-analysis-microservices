package br.com.ms.user_service.controller;

import br.com.ms.user_service.model.dto.request.UsuarioRequest;
import br.com.ms.user_service.model.dto.response.PageResponse;
import br.com.ms.user_service.model.dto.response.UsuarioResponse;
import br.com.ms.user_service.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/criar")
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest){
        UsuarioResponse usuario = usuarioService.criarUsuario(usuarioRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorId(@PathVariable Long usuarioId) {
        UsuarioResponse response = usuarioService.buscarUsuarioPorId(usuarioId);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@PathVariable Long usuarioId, @RequestBody UsuarioRequest request) {
        UsuarioResponse response = usuarioService.atualizarUsuario(request, usuarioId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<UsuarioResponse>> buscar(
            @RequestParam(required = false) String uf,
            @RequestParam(required = false) BigDecimal renda,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "1") int page) {

        Pageable pageable = PageRequest.of(page - 1, size);

        return ResponseEntity.ok(
                usuarioService.buscarComFiltros(uf, renda, pageable)
        );
    }

    @PostMapping("/seed")
    public ResponseEntity<Void> seed() {
        this.usuarioService.seedDB();
        return ResponseEntity.ok().build();
    }

}
