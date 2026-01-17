package org.maternite.sante.dto.response;

import lombok.Data;
import org.maternite.sante.model.enums.EtatGrossesse;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class GrossesseResponseDto {
    private Long id;
    private Long patienteId;
    private String patienteNom;
    private String patientePrenom;
    private LocalDate dateDebut;
    private LocalDate datePrevue;
    private EtatGrossesse etatGrossesse;
    private Integer nombreFoetus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
