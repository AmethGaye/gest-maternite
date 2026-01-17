package org.maternite.sante.dto.response;

import lombok.Data;
import org.maternite.sante.model.enums.RoleType;

import java.time.LocalDateTime;

@Data
public class UtilisateurResponseDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private RoleType role;
    private String telephone;
    private String matricule;
    private Boolean estDisponible;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
