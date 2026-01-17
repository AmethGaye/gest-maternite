package org.maternite.sante.dto.response;

import lombok.Builder;
import lombok.Data;
import org.maternite.sante.model.enums.StatutPlanning;
import org.maternite.sante.model.enums.TypeService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PlanningResponseDto {
    private Long id;
    private LocalDateTime datePlanning;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private TypeService typeService;
    private StatutPlanning statut;
    private List<AffectationResponseDto> affectations;
}
