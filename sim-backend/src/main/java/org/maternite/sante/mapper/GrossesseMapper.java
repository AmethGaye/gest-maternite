package org.maternite.sante.mapper;

import org.maternite.sante.dto.request.GrossesseRequestDto;
import org.maternite.sante.dto.response.GrossesseResponseDto;
import org.maternite.sante.model.Grossesse;
import org.springframework.stereotype.Component;

@Component
public class GrossesseMapper {

    public GrossesseResponseDto toDto(Grossesse grossesse) {
        if (grossesse == null) {
            return null;
        }

        return GrossesseResponseDto.builder()
                .id(grossesse.getId())
                .patienteId(grossesse.getPatiente() != null ? grossesse.getPatiente().getId() : null)
                .patienteNom(grossesse.getPatiente() != null ? grossesse.getPatiente().getNom() : null)
                .patientePrenom(grossesse.getPatiente() != null ? grossesse.getPatiente().getPrenom() : null)
                .dateDebut(grossesse.getDateDebut())
                .datePrevue(grossesse.getDatePrevue())
                .etatGrossesse(grossesse.getEtatGrossesse())
                .nombreFoetus(grossesse.getNombreFoetus())
                .createdAt(grossesse.getCreatedAt())
                .updatedAt(grossesse.getUpdatedAt())
                .build();
    }

    public Grossesse toEntity(GrossesseRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Grossesse.builder()
                .dateDebut(dto.getDateDebut())
                .datePrevue(dto.getDatePrevue())
                .etatGrossesse(dto.getEtatGrossesse())
                .nombreFoetus(dto.getNombreFoetus())
                .build();
    }
}
