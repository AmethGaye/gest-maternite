package org.maternite.sante.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.maternite.sante.model.enums.RoleType;

@Data
public class UtilisateurRequestDto {

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100)
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 100)
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotNull(message = "Le rôle est obligatoire")
    private RoleType role;

    @NotBlank(message = "Le téléphone est obligatoire")
    @Pattern(regexp = "^[0-9]{9,15}$", message = "Format de téléphone invalide")
    private String telephone;

    @Size(max = 50)
    private String matricule;

    private Boolean estDisponible = true;
}

