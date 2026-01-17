package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.request.PatienteRequestDto;
import org.maternite.sante.dto.response.PatienteResponseDto;
import org.maternite.sante.model.Patiente;

@Mapper(componentModel = "spring")
public interface PatienteMapper {

    PatienteResponseDto toDto(Patiente patiente);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dossierMedical", ignore = true)
    @Mapping(target = "grossesses", ignore = true)
    @Mapping(target = "consultations", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Patiente toEntity(PatienteRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dossierMedical", ignore = true)
    @Mapping(target = "grossesses", ignore = true)
    @Mapping(target = "consultations", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(PatienteRequestDto dto, @MappingTarget Patiente patiente);
}