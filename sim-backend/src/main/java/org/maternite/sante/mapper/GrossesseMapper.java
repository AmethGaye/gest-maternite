package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.request.GrossesseRequestDto;
import org.maternite.sante.dto.response.GrossesseResponseDto;
import org.maternite.sante.model.Grossesse;

@Mapper(componentModel = "spring")
public interface GrossesseMapper {

    @Mapping(source = "patiente.id", target = "patienteId")
    @Mapping(source = "patiente.nom", target = "patienteNom")
    @Mapping(source = "patiente.prenom", target = "patientePrenom")
    GrossesseResponseDto toDto(Grossesse grossesse);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patiente", ignore = true)
    @Mapping(target = "accouchement", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Grossesse toEntity(GrossesseRequestDto dto);
}
