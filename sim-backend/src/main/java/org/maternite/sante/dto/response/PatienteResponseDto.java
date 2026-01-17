package org.maternite.sante.dto.response;

import lombok.Builder;
import lombok.Data;
import org.maternite.sante.model.enums.GroupeSanguin;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class PatienteResponseDto {
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String adresse;
    private String telephone;
    private GroupeSanguin groupeSanguin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

