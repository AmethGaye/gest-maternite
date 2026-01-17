package org.maternite.sante.mapper;

import org.maternite.sante.dto.response.SalleAccouchementResponseDto;
import org.maternite.sante.model.SalleAccouchement;
import org.springframework.stereotype.Component;

@Component
public class SalleAccouchementMapper {

    public SalleAccouchementResponseDto toDto(SalleAccouchement salle) {
        if (salle == null) {
            return null;
        }

        return SalleAccouchementResponseDto.builder()
                .id(salle.getId())
                .numeroSalle(salle.getNumeroSalle())
                .statut(salle.getStatut())
                .estDisponible(salle.getEstDisponible())
                .createdAt(salle.getCreatedAt())
                .updatedAt(salle.getUpdatedAt())
                .build();
    }
}
