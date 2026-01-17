package org.maternite.sante.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class PrescriptionRequestDto {

    @NotNull(message = "L'ID du personnel est obligatoire")
    private Long utilisateurId;

    @NotNull(message = "L'ID de la patiente est obligatoire")
    private Long patienteId;

    @NotNull(message = "La date de prescription est obligatoire")
    private LocalDate datePrescription;

    private String typePatient;

    @NotEmpty(message = "Au moins un médicament est requis")
    private List<MedicamentRequestDto> medicaments;

    private String observations;
}
