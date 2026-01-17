package org.maternite.sante.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.maternite.sante.model.enums.TypeMedicament;

@Data
public class MedicamentRequestDto {

    @NotBlank(message = "Le nom du médicament est obligatoire")
    private String nom;

    @NotBlank(message = "Le dosage est obligatoire")
    private String dosage;

    @NotNull(message = "Le type de médicament est obligatoire")
    private TypeMedicament type;

    @NotNull(message = "La durée du traitement est obligatoire")
    @Min(value = 1, message = "La durée doit être au moins 1 jour")
    private Integer dureeTraitement;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 1, message = "La quantité doit être au moins 1")
    private Integer quantite;
}
