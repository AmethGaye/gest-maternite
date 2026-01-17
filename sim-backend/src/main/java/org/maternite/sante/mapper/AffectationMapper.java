package org.maternite.sante.mapper;

import org.maternite.sante.dto.request.AffectationRequestDto;
import org.maternite.sante.dto.response.AffectationResponseDto;
import org.maternite.sante.model.Affectation;
import org.maternite.sante.model.SalleAccouchement;
import org.maternite.sante.repository.SalleAccouchementRepository;
import org.springframework.stereotype.Component;

@Component
public class AffectationMapper {

    public AffectationResponseDto toDto(Affectation affectation) {
        if (affectation == null) {
            return null;
        }

        return AffectationResponseDto.builder()
                .id(affectation.getId())
                .planningId(affectation.getPlanning() != null ? affectation.getPlanning().getId() : null)
                .utilisateurId(affectation.getPersonnel() != null ? affectation.getPersonnel().getId() : null)
                .personnelNom(affectation.getPersonnel() != null ? affectation.getPersonnel().getNom() : null)
                .personnelPrenom(affectation.getPersonnel() != null ? affectation.getPersonnel().getPrenom() : null)
                .salleId(affectation.getSalle() != null ? affectation.getSalle().getId() : null)
                .numeroSalle(affectation.getSalle() != null ? affectation.getSalle().getNumeroSalle() : null)
                .dateAffectation(affectation.getCreatedAt())
                .statut(affectation.getStatut())
                .createdAt(affectation.getCreatedAt())
                .updatedAt(affectation.getUpdatedAt())
                .build();
    }

    public Affectation toEntity(AffectationRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return Affectation.builder()

                .statut(dto.getStatut())
                .build();
    }
}