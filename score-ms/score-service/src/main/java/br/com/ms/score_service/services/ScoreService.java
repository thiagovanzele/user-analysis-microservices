package br.com.ms.score_service.services;

import br.com.ms.score_service.clients.UserClient;
import br.com.ms.score_service.models.dto.response.PageResponse;
import br.com.ms.score_service.models.dto.response.UsuarioResponse;
import br.com.ms.score_service.models.dto.response.UsuarioScore;
import br.com.ms.score_service.models.enums.UF;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreService {

    private final UserClient userClient;

    public ScoreService(UserClient userClient) {
        this.userClient = userClient;
    }

    public List<UsuarioScore> buscarUsuariosPorRendaEUf(BigDecimal renda, String uf) {

        int page = 1;
        int size = 100;

        List<UsuarioScore> usuarios = new ArrayList<>();

        PageResponse<UsuarioResponse> response;

        do {
            response = userClient.buscarUsuariosPaginado(renda, uf, page, size);

            response.content().forEach(res -> {
                UsuarioScore usuarioScore = fromResponse(res);
                usuarios.add(usuarioScore);
            });

            page++;

        } while (!response.last());

        return usuarios;
    }

    public List<UsuarioScore> buscarUsuariosPorRendaEUf() {
        return this.buscarUsuariosPorRendaEUf(null, null);
    }

     private UsuarioScore fromResponse(UsuarioResponse response) {
         int basicScore = response.renda()
                 .divide(BigDecimal.valueOf(10), 0, RoundingMode.HALF_UP)
                 .intValue();

         if (response.uf().equals(UF.SP)) {
             basicScore += 200;
         } else if (response.uf().equals(UF.PR) ||
                 response.uf().equals(UF.RJ) ||
                 response.uf().equals(UF.RS)) {
             basicScore += 100;
         } else {
             basicScore -= 100;
         }

         return new UsuarioScore(response.email(), response.nome(), basicScore);

     }
}
