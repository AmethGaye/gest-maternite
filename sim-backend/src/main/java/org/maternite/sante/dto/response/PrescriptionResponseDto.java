package org.maternite.sante.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PrescriptionResponseDto {
    private Long id;
    private Long utilisateurId;
    private String personnelNom;
    private String personnelPrenom;
    private Long patienteId;
    private String patienteNom;
    private String patientePrenom;
    private LocalDate datePrescription;
    private String typePatient;
    private List<MedicamentResponseDto> medicaments;
    private String observations;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
