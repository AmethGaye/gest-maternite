package org.maternite.sante.mapper;

import org.maternite.sante.dto.request.PatienteRequestDto;
import org.maternite.sante.dto.response.PatienteResponseDto;
import org.maternite.sante.model.Patiente;
import org.springframework.stereotype.Component;

@Component
public class PatienteMapper {

    public PatienteResponseDto toDto(Patiente patiente) {
        if (patiente == null) {
            return null;
        }

        return PatienteResponseDto.builder()
                .id(patiente.getId())
                .nom(patiente.getNom())
                .prenom(patiente.getPrenom())
                .dateNaissance(patiente.getDateNaissance())
                .adresse(patiente.getAdresse())
                .telephone(patiente.getTelephone())
                .groupeSanguin(patiente.getGroupeSanguin())
                .createdAt(patiente.getCreatedAt())
                .updatedAt(patiente.getUpdatedAt())
                .build();
    }

    public Patiente toEntity(PatienteRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Patiente.builder()
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .dateNaissance(dto.getDateNaissance())
                .adresse(dto.getAdresse())
                .telephone(dto.getTelephone())
                .groupeSanguin(dto.getGroupeSanguin())
                .build();
    }

    public void updateEntityFromDto(PatienteRequestDto dto, Patiente patiente) {
        if (dto == null || patiente == null) {
            return;
        }

        if (dto.getNom() != null) {
            patiente.setNom(dto.getNom());
        }
        if (dto.getPrenom() != null) {
            patiente.setPrenom(dto.getPrenom());
        }
        if (dto.getDateNaissance() != null) {
            patiente.setDateNaissance(dto.getDateNaissance());
        }
        if (dto.getAdresse() != null) {
            patiente.setAdresse(dto.getAdresse());
        }
        if (dto.getTelephone() != null) {
            patiente.setTelephone(dto.getTelephone());
        }
        if (dto.getGroupeSanguin() != null) {
            patiente.setGroupeSanguin(dto.getGroupeSanguin());
        }
    }
}