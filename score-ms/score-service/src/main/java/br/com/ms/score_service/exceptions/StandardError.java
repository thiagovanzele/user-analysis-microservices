package br.com.ms.score_service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StandardError {

    private int status;
    private String message;
    private Instant timestamp;
    private String path;
}
