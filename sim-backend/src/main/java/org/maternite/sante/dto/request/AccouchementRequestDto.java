package org.maternite.sante.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.maternite.sante.model.enums.TypeAccouchement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AccouchementRequestDto {

    @NotNull(message = "L'ID de la grossesse est obligatoire")
    private Long grossesseId;

    private Long salleId;

    @NotNull(message = "La date d'accouchement est obligatoire")
    private LocalDateTime dateAccouchement;

    @NotNull(message = "L'heure de début est obligatoire")
    private LocalTime heureDebut;

    private LocalTime heureFin;

    @NotNull(message = "Le type d'accouchement est obligatoire")
    private TypeAccouchement type;

    private String complications;
}

