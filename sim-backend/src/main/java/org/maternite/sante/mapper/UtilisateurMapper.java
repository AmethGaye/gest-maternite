package org.maternite.sante.mapper;

import org.maternite.sante.dto.response.UtilisateurResponseDto;
import org.maternite.sante.model.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {

    public UtilisateurResponseDto toDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurResponseDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .role(utilisateur.getRole())
                .telephone(utilisateur.getTelephone())
                .matricule(utilisateur.getMatricule())
                .estDisponible(utilisateur.getEstDisponible())
                .createdAt(utilisateur.getCreatedAt())
                .updatedAt(utilisateur.getUpdatedAt())
                .build();
    }
}
