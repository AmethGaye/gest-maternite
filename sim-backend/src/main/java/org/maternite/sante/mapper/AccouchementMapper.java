package org.maternite.sante.mapper;

import org.maternite.sante.dto.request.AccouchementRequestDto;
import org.maternite.sante.dto.response.AccouchementResponseDto;
import org.maternite.sante.model.Accouchement;
import org.springframework.stereotype.Component;

@Component
public class AccouchementMapper {

    public AccouchementResponseDto toDto(Accouchement accouchement) {
        if (accouchement == null) {
            return null;
        }

        return AccouchementResponseDto.builder()
                .id(accouchement.getId())
                .grossesseId(accouchement.getGrossesse() != null ? accouchement.getGrossesse().getId() : null)
                .patienteId(accouchement.getGrossesse() != null && accouchement.getGrossesse().getPatiente() != null
                        ? accouchement.getGrossesse().getPatiente().getId() : null)
                .patienteNom(accouchement.getGrossesse() != null && accouchement.getGrossesse().getPatiente() != null
                        ? accouchement.getGrossesse().getPatiente().getNom() : null)
                .patientePrenom(accouchement.getGrossesse() != null && accouchement.getGrossesse().getPatiente() != null
                        ? accouchement.getGrossesse().getPatiente().getPrenom() : null)
                .salleId(accouchement.getSalle() != null ? accouchement.getSalle().getId() : null)
                .numeroSalle(accouchement.getSalle() != null ? accouchement.getSalle().getNumeroSalle() : null)
                .dateAccouchement(accouchement.getCreatedAt())
                .heureDebut(accouchement.getHeureDebut())
                .heureFin(accouchement.getHeureFin())
                .type(accouchement.getType())
                .complications(accouchement.getComplications())
                .createdAt(accouchement.getCreatedAt())
                .updatedAt(accouchement.getUpdatedAt())
                .build();
    }

    public Accouchement toEntity(AccouchementRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Accouchement.builder()
                .heureDebut(dto.getHeureDebut())
                .heureFin(dto.getHeureFin())
                .type(dto.getType())
                .complications(dto.getComplications())
                .build();
    }
}
