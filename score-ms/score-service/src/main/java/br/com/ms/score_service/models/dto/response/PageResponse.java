package br.com.ms.score_service.models.dto.response;

import java.util.List;


public record PageResponse<T>(
        List<T> content,
        int size,
        int page,
        long totalElements,
        int totalPages,
        boolean last
) {}
