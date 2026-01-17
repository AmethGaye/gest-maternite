package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.response.SalleAccouchementResponseDto;
import org.maternite.sante.model.SalleAccouchement;

@Mapper(componentModel = "spring")
public interface SalleAccouchementMapper {

    @Mapping(target = "accouchements", ignore = true)
    SalleAccouchementResponseDto toDto(SalleAccouchement salle);
}
