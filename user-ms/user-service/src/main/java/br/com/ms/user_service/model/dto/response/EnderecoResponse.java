package br.com.ms.user_service.model.dto.response;

public record EnderecoResponse(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf,
        Boolean erro
) {}