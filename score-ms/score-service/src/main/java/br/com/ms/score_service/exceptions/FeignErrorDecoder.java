package br.com.ms.score_service.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() == 400) {
            return new ArgumentoInvalidoException("Erro vindo do user-service");
        }

        return new Exception("Erro genérico");
    }
}
