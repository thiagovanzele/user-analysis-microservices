package br.com.ms.user_service.client;

import br.com.ms.user_service.model.dto.response.EnderecoResponse;
import br.com.ms.user_service.model.entities.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "geo-client", url = "http://localhost:8081/api/geo")
public interface GeoClient {

    @GetMapping("/{cep}")
    EnderecoResponse getViaCep(@PathVariable("cep") String cep);
}
