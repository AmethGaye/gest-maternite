package org.maternite.sante.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AffectationRequestDto {

    @NotNull(message = "L'ID du planning est obligatoire")
    private Long planningId;

    @NotNull(message = "L'ID du personnel est obligatoire")
    private Long utilisateurId;

    private Long salleId;

    @NotNull(message = "La date d'affectation est obligatoire")
    private LocalDate dateAffectation;

    @NotBlank(message = "Le statut est obligatoire")
    private String statut;
}
