package org.maternite.sante.mapper;

import org.maternite.sante.dto.request.ConsultationRequestDto;
import org.maternite.sante.dto.response.ConsultationResponseDto;
import org.maternite.sante.model.Consultation;
import org.springframework.stereotype.Component;

@Component
public class ConsultationMapper {

    public ConsultationResponseDto toDto(Consultation consultation) {
        if (consultation == null) {
            return null;
        }

        return ConsultationResponseDto.builder()
                .id(consultation.getId())
                .patienteId(consultation.getPatiente() != null ? consultation.getPatiente().getId() : null)
                .patienteNom(consultation.getPatiente() != null ? consultation.getPatiente().getNom() : null)
                .patientePrenom(consultation.getPatiente() != null ? consultation.getPatiente().getPrenom() : null)
                .nouveauNeId(consultation.getNouveauNe() != null ? consultation.getNouveauNe().getId() : null)
                .nouveauNeNom(consultation.getNouveauNe() != null ? consultation.getNouveauNe().getNom() : null)
                .nouveauNePrenom(consultation.getNouveauNe() != null ? consultation.getNouveauNe().getPrenom() : null)
                .utilisateurId(consultation.getPersonnel() != null ? consultation.getPersonnel().getId() : null)
                .personnelNom(consultation.getPersonnel() != null ? consultation.getPersonnel().getNom() : null)
                .personnelPrenom(consultation.getPersonnel() != null ? consultation.getPersonnel().getPrenom() : null)
                .dateConsultation(consultation.getDateConsultation())
                .poids(consultation.getPoids())
                .temperature(consultation.getTemperature())
                .tensionArterielle(consultation.getTensionArterielle())
                .observations(consultation.getObservations())
                .createdAt(consultation.getCreatedAt())
                .updatedAt(consultation.getUpdatedAt())
                .build();
    }

    public Consultation toEntity(ConsultationRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Consultation.builder()
                .dateConsultation(dto.getDateConsultation())
                .poids(dto.getPoids())
                .temperature(dto.getTemperature())
                .tensionArterielle(dto.getTensionArterielle())
                .observations(dto.getObservations())
                .build();
    }
}