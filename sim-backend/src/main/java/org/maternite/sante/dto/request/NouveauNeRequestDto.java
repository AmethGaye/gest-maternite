package org.maternite.sante.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.maternite.sante.model.enums.Sexe;

import java.time.LocalDate;

@Data
public class NouveauNeRequestDto {

    @NotNull(message = "L'ID de l'accouchement est obligatoire")
    private Long accouchementId;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotNull(message = "Le sexe est obligatoire")
    private Sexe sexe;

    @NotNull(message = "Le poids de naissance est obligatoire")
    @DecimalMin(value = "0.5", message = "Le poids doit être au moins 0.5 kg")
    @DecimalMax(value = "10.0", message = "Le poids ne peut pas dépasser 10 kg")
    private Double poidsNaissance;

    @NotNull(message = "La taille de naissance est obligatoire")
    @DecimalMin(value = "20.0", message = "La taille doit être au moins 20 cm")
    @DecimalMax(value = "80.0", message = "La taille ne peut pas dépasser 80 cm")
    private Double tailleNaissance;

    @NotNull(message = "La date de naissance est obligatoire")
    private LocalDate dateNaissance;
}

