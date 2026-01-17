package org.maternite.sante.dto.response;

import lombok.Builder;
import lombok.Data;
import org.maternite.sante.model.enums.TypeAccouchement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Data
@Builder
public class AccouchementResponseDto {
    private Long id;
    private Long grossesseId;
    private Long patienteId;
    private String patienteNom;
    private String patientePrenom;
    private Long salleId;
    private Integer numeroSalle;
    private LocalDateTime dateAccouchement;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private TypeAccouchement type;
    private String complications;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

