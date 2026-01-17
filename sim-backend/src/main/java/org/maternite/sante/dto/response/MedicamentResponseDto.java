package org.maternite.sante.dto.response;

import lombok.Data;
import org.maternite.sante.model.enums.TypeMedicament;

import java.time.LocalDateTime;

@Data
public class MedicamentResponseDto {
    private Long id;
    private String nom;
    private String dosage;
    private TypeMedicament type;
    private Integer dureeTraitement;
    private Integer quantite;
    private LocalDateTime createdAt;
}
