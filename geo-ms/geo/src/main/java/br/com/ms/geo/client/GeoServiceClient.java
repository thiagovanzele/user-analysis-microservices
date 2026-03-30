package br.com.ms.geo.client;

import br.com.ms.geo.dto.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "geo-service-client", url = "https://viacep.com.br/ws")
public interface GeoServiceClient {

    @GetMapping("/{cep}/json")
    ViaCepResponse buscarCep(@PathVariable("cep") String cep);

}
