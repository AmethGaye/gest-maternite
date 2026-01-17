package org.maternite.sante.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.maternite.sante.model.enums.EtatGrossesse;

import java.time.LocalDate;

@Data
public class GrossesseRequestDto {

    @NotNull(message = "L'ID de la patiente est obligatoire")
    private Long patienteId;

    @NotNull(message = "La date de début est obligatoire")
    private LocalDate dateDebut;

    private LocalDate datePrevue;

    @NotNull(message = "L'état de la grossesse est obligatoire")
    private EtatGrossesse etatGrossesse;

    @Min(value = 1, message = "Le nombre de fœtus doit être au moins 1")
    @Max(value = 5, message = "Le nombre de fœtus ne peut pas dépasser 5")
    private Integer nombreFoetus = 1;
}
