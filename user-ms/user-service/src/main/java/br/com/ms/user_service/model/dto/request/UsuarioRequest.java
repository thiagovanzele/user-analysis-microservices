package br.com.ms.user_service.model.dto.request;

import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record UsuarioRequest(String nome,
                             @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "CEP inválido") String cep,
                             BigDecimal renda) {}
