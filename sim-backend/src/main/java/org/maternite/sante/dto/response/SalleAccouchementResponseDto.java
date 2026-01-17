package org.maternite.sante.dto.response;

import lombok.Builder;
import lombok.Data;
import org.maternite.sante.model.enums.StatutSalle;

import java.time.LocalDateTime;

@Data
@Builder
public class SalleAccouchementResponseDto {
    private Long id;
    private Integer numeroSalle;
    private StatutSalle statut;
    private Boolean estDisponible;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
