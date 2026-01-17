package org.maternite.sante.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ConsultationRequestDto {

    private Long patienteId;
    private Long nouveauNeId;

    @NotNull(message = "L'ID du personnel est obligatoire")
    private Long utilisateurId;

    @NotNull(message = "La date de consultation est obligatoire")
    private LocalDate dateConsultation;

    @DecimalMin(value = "0.5")
    @DecimalMax(value = "300.0")
    private Double poids;

    @DecimalMin(value = "30.0")
    @DecimalMax(value = "45.0")
    private Double temperature;

    @Min(value = 50)
    @Max(value = 250)
    private Integer tensionArterielle;

    private String observations;
}

