package br.com.ms.user_service.model.enums;

import br.com.ms.user_service.exceptions.ArgumentoInvalidoException;

import java.util.Arrays;
import java.util.Optional;

public enum UF {
    AC, // Acre
    AL, // Alagoas
    AP, // Amapá
    AM, // Amazonas
    BA, // Bahia
    CE, // Ceará
    DF, // Distrito Federal
    ES, // Espírito Santo
    GO, // Goiás
    MA, // Maranhão
    MT, // Mato Grosso
    MS, // Mato Grosso do Sul
    MG, // Minas Gerais
    PA, // Pará
    PB, // Paraíba
    PR, // Paraná
    PE, // Pernambuco
    PI, // Piauí
    RJ, // Rio de Janeiro
    RN, // Rio Grande do Norte
    RS, // Rio Grande do Sul
    RO, // Rondônia
    RR, // Roraima
    SC, // Santa Catarina
    SP, // São Paulo
    SE, // Sergipe
    TO;  // Tocantins

    public static Optional<UF> fromApi(String ufFromApi) {
        return Arrays.stream(values())
                .filter(uf -> uf.name().equalsIgnoreCase(ufFromApi))
                .findFirst();
    }

}
