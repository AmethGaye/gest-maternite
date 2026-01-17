package org.maternite.sante.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class AffectationResponseDto {
    private Long id;
    private Long planningId;
    private Long utilisateurId;
    private String personnelNom;
    private String personnelPrenom;
    private Long salleId;
    private Integer numeroSalle;
    private LocalDateTime dateAffectation;
    private String statut;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}