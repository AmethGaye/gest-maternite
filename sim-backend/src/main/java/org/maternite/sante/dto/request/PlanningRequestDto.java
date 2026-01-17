package org.maternite.sante.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.maternite.sante.model.enums.StatutPlanning;
import org.maternite.sante.model.enums.TypeService;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PlanningRequestDto {

    @NotNull(message = "La date du planning est obligatoire")
    private LocalDate datePlanning;

    @NotNull(message = "L'heure de début est obligatoire")
    private LocalTime heureDebut;

    @NotNull(message = "L'heure de fin est obligatoire")
    private LocalTime heureFin;

    @NotNull(message = "Le type de service est obligatoire")
    private TypeService typeService;

    @NotNull(message = "Le statut est obligatoire")
    private StatutPlanning statut;
}
