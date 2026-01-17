package org.maternite.sante.dto.request;

import lombok.Data;

@Data
public class DossierMedicalRequestDto {

    private String antecedentsMedicaux;
    private String antecedentsChirurgicaux;
    private String antecedentsObstetricaux;
    private String allergies;
    private String observations;
}
