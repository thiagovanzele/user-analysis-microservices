package br.com.ms.user_service.model.entities;

import br.com.ms.user_service.model.dto.response.EnderecoResponse;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Endereco {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public static Endereco of(EnderecoResponse response) {

        if (response == null) {
            throw new IllegalArgumentException("Endereço não pode ser null");
        }

        return builder()
                .bairro(response.bairro())
                .cep(response.cep())
                .logradouro(response.logradouro())
                .localidade(response.localidade())
                .uf(response.uf())
                .build();
    }
}
