package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.request.RegisterRequest;
import org.maternite.sante.dto.response.UtilisateurResponseDto;
import org.maternite.sante.model.Utilisateur;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    UtilisateurResponseDto toDto(Utilisateur utilisateur);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Utilisateur toEntity(RegisterRequest dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(RegisterRequest dto, @MappingTarget Utilisateur utilisateur);
}
