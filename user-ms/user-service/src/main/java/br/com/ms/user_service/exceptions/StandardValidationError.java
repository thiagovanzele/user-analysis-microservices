package br.com.ms.user_service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardValidationError {

    List<String> messages;
    Integer status;
    String path;
    Instant timestamp = Instant.now();

}
