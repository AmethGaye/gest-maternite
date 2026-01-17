package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.request.AffectationRequestDto;
import org.maternite.sante.dto.response.AffectationResponseDto;
import org.maternite.sante.model.Affectation;

@Mapper(componentModel = "spring")
public interface AffectationMapper {

    @Mapping(source = "planning.id", target = "planningId")
    @Mapping(source = "personnel.id", target = "utilisateurId")
    @Mapping(source = "personnel.nom", target = "personnelNom")
    @Mapping(source = "personnel.prenom", target = "personnelPrenom")
    @Mapping(source = "salle.id", target = "salleId")
    @Mapping(source = "salle.numeroSalle", target = "numeroSalle")
    AffectationResponseDto toDto(Affectation affectation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "planning", ignore = true)
    @Mapping(target = "personnel", ignore = true)
    @Mapping(target = "salle", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Affectation toEntity(AffectationRequestDto dto);
}
