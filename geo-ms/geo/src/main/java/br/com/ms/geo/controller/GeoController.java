package br.com.ms.geo.controller;

import br.com.ms.geo.dto.ViaCepResponse;
import br.com.ms.geo.service.GeoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/geo")
public class GeoController {

    private final GeoService geoService;

    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }
    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepResponse> buscarCep(@PathVariable String cep) {
        ViaCepResponse response = geoService.buscarCep(cep);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
