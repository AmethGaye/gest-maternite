package org.maternite.sante.mapper;

import org.maternite.sante.dto.request.DossierMedicalRequestDto;
import org.maternite.sante.dto.response.DossierMedicalResponseDto;
import org.maternite.sante.model.DossierMedical;
import org.springframework.stereotype.Component;

@Component
public class DossierMedicalMapper {

    public DossierMedicalResponseDto toDto(DossierMedical dossier) {
        if (dossier == null) {
            return null;
        }

        return DossierMedicalResponseDto.builder()
                .id(dossier.getId())
                .patienteId(dossier.getPatiente() != null ? dossier.getPatiente().getId() : null)
                .patienteNom(dossier.getPatiente() != null ? dossier.getPatiente().getNom() : null)
                .patientePrenom(dossier.getPatiente() != null ? dossier.getPatiente().getPrenom() : null)
                .antecedentsMedicaux(dossier.getAntecedentsMedicaux())
                .antecedentsChirurgicaux(dossier.getAntecedentsChirurgicaux())
                .antecedentsObstetricaux(dossier.getAntecedentsObstetricaux())
                .allergies(dossier.getAllergies())
                .observations(dossier.getObservations())
                .createdAt(dossier.getCreatedAt())
                .updatedAt(dossier.getUpdatedAt())
                .build();
    }

    public DossierMedical toEntity(DossierMedicalRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return DossierMedical.builder()
                .antecedentsMedicaux(dto.getAntecedentsMedicaux())
                .antecedentsChirurgicaux(dto.getAntecedentsChirurgicaux())
                .antecedentsObstetricaux(dto.getAntecedentsObstetricaux())
                .allergies(dto.getAllergies())
                .observations(dto.getObservations())
                .build();
    }

    public void updateEntityFromDto(DossierMedicalRequestDto dto, DossierMedical dossier) {
        if (dto == null || dossier == null) {
            return;
        }

        if (dto.getAntecedentsMedicaux() != null) {
            dossier.setAntecedentsMedicaux(dto.getAntecedentsMedicaux());
        }
        if (dto.getAntecedentsChirurgicaux() != null) {
            dossier.setAntecedentsChirurgicaux(dto.getAntecedentsChirurgicaux());
        }
        if (dto.getAntecedentsObstetricaux() != null) {
            dossier.setAntecedentsObstetricaux(dto.getAntecedentsObstetricaux());
        }
        if (dto.getAllergies() != null) {
            dossier.setAllergies(dto.getAllergies());
        }
        if (dto.getObservations() != null) {
            dossier.setObservations(dto.getObservations());
        }
    }
}