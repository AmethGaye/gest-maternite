package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.request.DossierNeonatalRequestDto;
import org.maternite.sante.dto.response.DossierNeonatalResponseDto;
import org.maternite.sante.model.DossierNeonatal;

@Mapper(componentModel = "spring")
public interface DossierNeonatalMapper {

    @Mapping(source = "nouveauNe.id", target = "nouveauNeId")
    @Mapping(source = "nouveauNe.nom", target = "nouveauNeNom")
    @Mapping(source = "nouveauNe.prenom", target = "nouveauNePrenom")
    DossierNeonatalResponseDto toDto(DossierNeonatal dossierNeonatal);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nouveauNe", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DossierNeonatal toEntity(DossierNeonatalRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nouveauNe", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(DossierNeonatalRequestDto dto, @MappingTarget DossierNeonatal dossierNeonatal);
}
