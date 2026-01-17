package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.request.SoinRequestDto;
import org.maternite.sante.dto.response.SoinResponseDto;
import org.maternite.sante.model.Soin;

@Mapper(componentModel = "spring")
public interface SoinMapper {

    @Mapping(source = "nouveauNe.id", target = "nouveauNeId")
    @Mapping(source = "nouveauNe.nom", target = "nouveauNeNom")
    @Mapping(source = "nouveauNe.prenom", target = "nouveauNePrenom")
    SoinResponseDto toDto(Soin soin);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nouveauNe", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Soin toEntity(SoinRequestDto dto);
}
