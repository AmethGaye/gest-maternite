package org.maternite.sante.mapper;

import org.maternite.sante.dto.request.SoinRequestDto;
import org.maternite.sante.dto.response.SoinResponseDto;
import org.maternite.sante.model.Soin;
import org.springframework.stereotype.Component;

@Component
public class SoinMapper {

    public SoinResponseDto toDto(Soin soin) {
        if (soin == null) {
            return null;
        }

        return SoinResponseDto.builder()
                .id(soin.getId())
                .nouveauNeId(soin.getNouveauNe() != null ? soin.getNouveauNe().getId() : null)
                .nouveauNeNom(soin.getNouveauNe() != null ? soin.getNouveauNe().getNom() : null)
                .nouveauNePrenom(soin.getNouveauNe() != null ? soin.getNouveauNe().getPrenom() : null)
                .typeSoin(soin.getTypeSoin())
                .poids(soin.getPoids())
                .temperature(soin.getTemperature())
                .tensionArterielle(soin.getTensionArterielle())
                .dateSoin(soin.getCreatedAt())
                .observations(soin.getObservations())
                .createdAt(soin.getCreatedAt())
                .updatedAt(soin.getUpdatedAt())
                .build();
    }

    public Soin toEntity(SoinRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Soin.builder()
                .typeSoin(dto.getTypeSoin())
                .poids(dto.getPoids())
                .temperature(dto.getTemperature())
                .tensionArterielle(dto.getTensionArterielle())
                .observations(dto.getObservations())
                .build();
    }
}