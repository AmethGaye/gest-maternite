package org.maternite.sante.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.maternite.sante.model.enums.RoleType;

@Data
public class UtilisateurUpdateRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotNull(message = "Le rôle est obligatoire")
    private RoleType role;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    private String matricule;

    private Boolean estDisponible;
}
