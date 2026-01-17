package org.maternite.sante.mapper;

import org.maternite.sante.dto.request.MedicamentRequestDto;
import org.maternite.sante.dto.response.MedicamentResponseDto;
import org.maternite.sante.model.Medicament;
import org.springframework.stereotype.Component;

@Component
public class MedicamentMapper {

    public MedicamentResponseDto toDto(Medicament medicament) {
        if (medicament == null) {
            return null;
        }

        return MedicamentResponseDto.builder()
                .id(medicament.getId())
                .nom(medicament.getNom())
                .dosage(medicament.getDosage())
                .type(medicament.getType())
                .dureeTraitement(medicament.getDureeTraitement())
                .quantite(medicament.getQuantite())
                .createdAt(medicament.getCreatedAt())
                .build();
    }

    public Medicament toEntity(MedicamentRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Medicament.builder()
                .nom(dto.getNom())
                .dosage(dto.getDosage())
                .type(dto.getType())
                .dureeTraitement(dto.getDureeTraitement())
                .quantite(dto.getQuantite())
                .build();
    }
}