package org.maternite.sante.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AffectationResponseDto {
    private Long id;
    private Long planningId;
    private Long utilisateurId;
    private String personnelNom;
    private String personnelPrenom;
    private Long salleId;
    private Integer numeroSalle;
    private LocalDate dateAffectation;
    private String statut;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}