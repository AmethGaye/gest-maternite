package org.maternite.sante.mapper;

import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.PlanningRequestDto;
import org.maternite.sante.dto.response.PlanningResponseDto;
import org.maternite.sante.model.Planning;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlanningMapper {

    private final AffectationMapper affectationMapper;

    public PlanningResponseDto toDto(Planning planning) {
        if (planning == null) {
            return null;
        }

        return PlanningResponseDto.builder()
                .id(planning.getId())
                .datePlanning(planning.getCreatedAt())
                .heureDebut(planning.getHeureDebut())
                .heureFin(planning.getHeureFin())
                .typeService(planning.getTypeService())
                .statut(planning.getStatut())
                .affectations(planning.getAffectations() != null
                        ? planning.getAffectations().stream()
                        .map(affectationMapper::toDto)
                        .collect(Collectors.toList())
                        : null)
                .build();
    }

    public Planning toEntity(PlanningRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Planning.builder()
                .heureDebut(dto.getHeureDebut())
                .heureFin(dto.getHeureFin())
                .typeService(dto.getTypeService())
                .statut(dto.getStatut())
                .build();
    }
}