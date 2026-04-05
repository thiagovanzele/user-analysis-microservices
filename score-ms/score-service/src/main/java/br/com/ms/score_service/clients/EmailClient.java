package br.com.ms.score_service.clients;

import br.com.ms.score_service.models.dto.request.EmailRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-ms", url = "http://localhost:8083/api/email")
public interface EmailClient {

    @PostMapping("/enviar")
    void enviarEmail(@RequestBody EmailRequest request);
}
