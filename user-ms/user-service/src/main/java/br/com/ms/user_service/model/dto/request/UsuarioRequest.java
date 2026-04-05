package br.com.ms.user_service.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record UsuarioRequest(@NotEmpty(message = "Nome do usuário é obrigatório")
                             String nome,
                             @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "CEP inválido")
                             @NotEmpty(message = "CEP é obrigatório")
                             String cep,
                             @Email
                             @NotEmpty(message = "Email é obrigatório")
                             String email,
                             BigDecimal renda) {}
