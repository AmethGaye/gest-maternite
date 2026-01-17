package org.maternite.sante.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class SoinRequestDto {

    @NotNull(message = "L'ID du nouveau-né est obligatoire")
    private Long nouveauNeId;

    @NotBlank(message = "Le type de soin est obligatoire")
    private String typeSoin;

    private Double poids;
    private Double temperature;
    private Integer tensionArterielle;

    @NotNull(message = "La date du soin est obligatoire")
    private LocalDate dateSoin;

    private String observations;
}
