package br.com.ms.geo.service;

import br.com.ms.geo.client.GeoServiceClient;
import br.com.ms.geo.dto.ViaCepResponse;
import br.com.ms.geo.exception.CepInvalidoException;
import org.springframework.stereotype.Service;

@Service
public class GeoService {

    private final GeoServiceClient client;

    public GeoService(GeoServiceClient geoServiceClient) {
        this.client = geoServiceClient;
    }

    public ViaCepResponse buscarCep(String cep) {
        cep = cep.replace("-", "").trim();
        var response = client.buscarCep(cep);

        if (response.erro() != null && response.erro()) {
            throw new CepInvalidoException();
        }

        return response;
    }
}
