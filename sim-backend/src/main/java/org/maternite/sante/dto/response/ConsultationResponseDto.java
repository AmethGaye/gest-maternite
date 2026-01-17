package org.maternite.sante.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ConsultationResponseDto {
    private Long id;
    private Long patienteId;
    private String patienteNom;
    private String patientePrenom;
    private Long nouveauNeId;
    private String nouveauNeNom;
    private String nouveauNePrenom;
    private Long utilisateurId;
    private String personnelNom;
    private String personnelPrenom;
    private LocalDate dateConsultation;
    private Double poids;
    private Double temperature;
    private Integer tensionArterielle;
    private String observations;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}