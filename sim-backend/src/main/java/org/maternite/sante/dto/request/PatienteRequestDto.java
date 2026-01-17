package org.maternite.sante.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.maternite.sante.model.enums.GroupeSanguin;

import java.time.LocalDate;

@Data
public class PatienteRequestDto {

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100)
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 100)
    private String prenom;

    @NotNull(message = "La date de naissance est obligatoire")
    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate dateNaissance;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;

    @NotBlank(message = "Le téléphone est obligatoire")
    @Pattern(regexp = "^[0-9]{9,15}$")
    private String telephone;

    @NotNull(message = "Le groupe sanguin est obligatoire")
    private GroupeSanguin groupeSanguin;
}
