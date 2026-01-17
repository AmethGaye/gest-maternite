package org.maternite.sante.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class DossierMedicalResponseDto {
    private Long id;
    private Long patienteId;
    private String patienteNom;
    private String patientePrenom;
    private String antecedentsMedicaux;
    private String antecedentsChirurgicaux;
    private String antecedentsObstetricaux;
    private String allergies;
    private String observations;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
