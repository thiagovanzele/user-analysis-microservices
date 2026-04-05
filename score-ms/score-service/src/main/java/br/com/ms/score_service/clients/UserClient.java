package br.com.ms.score_service.clients;

import br.com.ms.score_service.models.dto.response.PageResponse;
import br.com.ms.score_service.models.dto.response.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "user-client", url = "http://localhost:8080/api/usuario")
public interface UserClient {

    @GetMapping
    PageResponse<UsuarioResponse> buscarUsuariosPaginado(
            @RequestParam(name = "renda", required = false) BigDecimal renda,
            @RequestParam(name = "uf", required = false) String uf,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size
    );
}
