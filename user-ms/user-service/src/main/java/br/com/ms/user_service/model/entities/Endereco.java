package br.com.ms.user_service.model.entities;

import br.com.ms.user_service.model.dto.response.EnderecoResponse;
import br.com.ms.user_service.model.enums.UF;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

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

    @Enumerated(EnumType.STRING)
    private UF uf;

    public static Endereco of(EnderecoResponse response) {

        if (response == null) {
            throw new IllegalArgumentException("Endereço não pode ser null");
        }

        Optional<UF> ufFromResponse = UF.fromApi(response.uf());

        EnderecoBuilder enderecoBuilder = builder()
                .bairro(response.bairro())
                .cep(response.cep())
                .logradouro(response.logradouro())
                .localidade(response.localidade());

        ufFromResponse.ifPresent(enderecoBuilder::uf);

        return enderecoBuilder.build();
    }
}
