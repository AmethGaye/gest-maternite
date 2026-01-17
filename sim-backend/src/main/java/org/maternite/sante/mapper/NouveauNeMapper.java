package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.request.NouveauNeRequestDto;
import org.maternite.sante.dto.response.NouveauNeResponseDto;
import org.maternite.sante.model.NouveauNe;

@Mapper(componentModel = "spring")
public interface NouveauNeMapper {

    @Mapping(source = "accouchement.id", target = "accouchementId")
    NouveauNeResponseDto toDto(NouveauNe nouveauNe);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accouchement", ignore = true)
    @Mapping(target = "dossierNeonatal", ignore = true)
    @Mapping(target = "consultations", ignore = true)
    @Mapping(target = "soins", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    NouveauNe toEntity(NouveauNeRequestDto dto);
}