package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.request.AccouchementRequestDto;
import org.maternite.sante.dto.response.AccouchementResponseDto;
import org.maternite.sante.model.Accouchement;;

@Mapper(componentModel = "spring")
public interface AccouchementMapper {

    @Mapping(source = "grossesse.id", target = "grossesseId")
    @Mapping(source = "grossesse.patiente.id", target = "patienteId")
    @Mapping(source = "grossesse.patiente.nom", target = "patienteNom")
    @Mapping(source = "grossesse.patiente.prenom", target = "patientePrenom")
    @Mapping(source = "salle.id", target = "salleId")
    @Mapping(source = "salle.numeroSalle", target = "numeroSalle")
    AccouchementResponseDto toDto(Accouchement accouchement);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "grossesse", ignore = true)
    @Mapping(target = "salle", ignore = true)
    @Mapping(target = "nouveauNes", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Accouchement toEntity(AccouchementRequestDto dto);
}

