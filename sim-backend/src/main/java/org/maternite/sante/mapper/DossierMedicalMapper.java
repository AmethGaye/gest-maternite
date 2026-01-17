package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.request.DossierMedicalRequestDto;
import org.maternite.sante.dto.response.DossierMedicalResponseDto;
import org.maternite.sante.model.DossierMedical;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {

    @Mapping(source = "patiente.id", target = "patienteId")
    @Mapping(source = "patiente.nom", target = "patienteNom")
    @Mapping(source = "patiente.prenom", target = "patientePrenom")
    DossierMedicalResponseDto toDto(DossierMedical dossierMedical);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patiente", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DossierMedical toEntity(DossierMedicalRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patiente", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(DossierMedicalRequestDto dto, @MappingTarget DossierMedical dossierMedical);
}