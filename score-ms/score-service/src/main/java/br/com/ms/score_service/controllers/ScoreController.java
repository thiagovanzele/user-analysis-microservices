package br.com.ms.score_service.controllers;

import br.com.ms.score_service.models.dto.response.PageResponse;
import br.com.ms.score_service.models.dto.response.UsuarioResponse;
import br.com.ms.score_service.models.dto.response.UsuarioScore;
import br.com.ms.score_service.services.ScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioScore>> buscar(@RequestParam(required = false) BigDecimal renda,
                                                     @RequestParam(required = false) String uf) {
        List<UsuarioScore> response = this.scoreService.buscarUsuariosPorRendaEUf(renda, uf);

        return ResponseEntity.ok().body(response);
    }
}
