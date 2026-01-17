package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.request.MedicamentRequestDto;
import org.maternite.sante.dto.response.MedicamentResponseDto;
import org.maternite.sante.model.Medicament;

@Mapper(componentModel = "spring")
public interface MedicamentMapper {

    MedicamentResponseDto toDto(Medicament medicament);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "prescription", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Medicament toEntity(MedicamentRequestDto dto);
}