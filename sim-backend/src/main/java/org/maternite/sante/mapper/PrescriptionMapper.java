package org.maternite.sante.mapper;

import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.PrescriptionRequestDto;
import org.maternite.sante.dto.response.PrescriptionResponseDto;
import org.maternite.sante.model.Prescription;
import org.springframework.stereotype.Component;


import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PrescriptionMapper {

    private final MedicamentMapper medicamentMapper;

    public PrescriptionResponseDto toDto(Prescription prescription) {
        if (prescription == null) {
            return null;
        }

        return PrescriptionResponseDto.builder()
                .id(prescription.getId())
                .utilisateurId(prescription.getPersonnel() != null ? prescription.getPersonnel().getId() : null)
                .personnelNom(prescription.getPersonnel() != null ? prescription.getPersonnel().getNom() : null)
                .personnelPrenom(prescription.getPersonnel() != null ? prescription.getPersonnel().getPrenom() : null)
                .patienteId(prescription.getPatiente() != null ? prescription.getPatiente().getId() : null)
                .patienteNom(prescription.getPatiente() != null ? prescription.getPatiente().getNom() : null)
                .patientePrenom(prescription.getPatiente() != null ? prescription.getPatiente().getPrenom() : null)
                .datePrescription(prescription.getCreatedAt())
                .typePatient(prescription.getTypePatient())
                .medicaments(prescription.getMedicaments() != null
                        ? prescription.getMedicaments().stream()
                        .map(medicamentMapper::toDto)
                        .collect(Collectors.toList())
                        : null)
                .observations(prescription.getObservations())
                .createdAt(prescription.getCreatedAt())
                .updatedAt(prescription.getUpdatedAt())
                .build();
    }

    public Prescription toEntity(PrescriptionRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Prescription.builder()
                .typePatient(dto.getTypePatient())
                .observations(dto.getObservations())
                .build();
    }
}