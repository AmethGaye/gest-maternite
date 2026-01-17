package org.maternite.sante.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class SoinResponseDto {
    private Long id;
    private Long nouveauNeId;
    private String nouveauNeNom;
    private String nouveauNePrenom;
    private String typeSoin;
    private Double poids;
    private Double temperature;
    private Integer tensionArterielle;
    private LocalDateTime dateSoin;
    private String observations;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
