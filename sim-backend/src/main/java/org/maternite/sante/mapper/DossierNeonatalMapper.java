package org.maternite.sante.mapper;

import org.maternite.sante.dto.request.DossierNeonatalRequestDto;
import org.maternite.sante.dto.response.DossierNeonatalResponseDto;
import org.maternite.sante.model.DossierNeonatal;
import org.springframework.stereotype.Component;

@Component
public class DossierNeonatalMapper {

    public DossierNeonatalResponseDto toDto(DossierNeonatal dossier) {
        if (dossier == null) {
            return null;
        }

        return DossierNeonatalResponseDto.builder()
                .id(dossier.getId())
                .nouveauNeId(dossier.getNouveauNe() != null ? dossier.getNouveauNe().getId() : null)
                .nouveauNeNom(dossier.getNouveauNe() != null ? dossier.getNouveauNe().getNom() : null)
                .nouveauNePrenom(dossier.getNouveauNe() != null ? dossier.getNouveauNe().getPrenom() : null)
                .facteurRh(dossier.getFacteurRh())
                .reanimation(dossier.getReanimation())
                .detresseRespiratoire(dossier.getDetresseRespiratoire())
                .testGuthrie(dossier.getTestGuthrie())
                .depistage(dossier.getDepistage())
                .observations(dossier.getObservations())
                .createdAt(dossier.getCreatedAt())
                .updatedAt(dossier.getUpdatedAt())
                .build();
    }

    public DossierNeonatal toEntity(DossierNeonatalRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return DossierNeonatal.builder()
                .facteurRh(dto.getFacteurRh())
                .reanimation(dto.getReanimation())
                .detresseRespiratoire(dto.getDetresseRespiratoire())
                .testGuthrie(dto.getTestGuthrie())
                .depistage(dto.getDepistage())
                .observations(dto.getObservations())
                .build();
    }

    public void updateEntityFromDto(DossierNeonatalRequestDto dto, DossierNeonatal dossier) {
        if (dto == null || dossier == null) {
            return;
        }

        if (dto.getFacteurRh() != null) {
            dossier.setFacteurRh(dto.getFacteurRh());
        }
        if (dto.getReanimation() != null) {
            dossier.setReanimation(dto.getReanimation());
        }
        if (dto.getDetresseRespiratoire() != null) {
            dossier.setDetresseRespiratoire(dto.getDetresseRespiratoire());
        }
        if (dto.getTestGuthrie() != null) {
            dossier.setTestGuthrie(dto.getTestGuthrie());
        }
        if (dto.getDepistage() != null) {
            dossier.setDepistage(dto.getDepistage());
        }
        if (dto.getObservations() != null) {
            dossier.setObservations(dto.getObservations());
        }
    }
}
