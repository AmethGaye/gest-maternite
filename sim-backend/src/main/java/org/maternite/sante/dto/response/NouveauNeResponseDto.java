package org.maternite.sante.dto.response;

import lombok.Data;
import org.maternite.sante.model.enums.Sexe;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class NouveauNeResponseDto {
    private Long id;
    private Long accouchementId;
    private String nom;
    private String prenom;
    private Sexe sexe;
    private Double poidsNaissance;
    private Double tailleNaissance;
    private LocalDate dateNaissance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
