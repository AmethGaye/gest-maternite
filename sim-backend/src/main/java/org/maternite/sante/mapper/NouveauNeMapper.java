package org.maternite.sante.mapper;

import org.maternite.sante.dto.request.NouveauNeRequestDto;
import org.maternite.sante.dto.response.NouveauNeResponseDto;
import org.maternite.sante.model.NouveauNe;
import org.springframework.stereotype.Component;

@Component
public class NouveauNeMapper {

    public NouveauNeResponseDto toDto(NouveauNe nouveauNe) {
        if (nouveauNe == null) {
            return null;
        }

        return NouveauNeResponseDto.builder()
                .id(nouveauNe.getId())
                .accouchementId(nouveauNe.getAccouchement() != null ? nouveauNe.getAccouchement().getId() : null)
                .nom(nouveauNe.getNom())
                .prenom(nouveauNe.getPrenom())
                .sexe(nouveauNe.getSexe())
                .poidsNaissance(nouveauNe.getPoidsNaissance())
                .tailleNaissance(nouveauNe.getTailleNaissance())
                .dateNaissance(nouveauNe.getDateNaissance())
                .createdAt(nouveauNe.getCreatedAt())
                .updatedAt(nouveauNe.getUpdatedAt())
                .build();
    }

    public NouveauNe toEntity(NouveauNeRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return NouveauNe.builder()
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .sexe(dto.getSexe())
                .poidsNaissance(dto.getPoidsNaissance())
                .tailleNaissance(dto.getTailleNaissance())
                .dateNaissance(dto.getDateNaissance())
                .build();
    }
}