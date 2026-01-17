package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.request.PlanningRequestDto;
import org.maternite.sante.dto.response.PlanningResponseDto;
import org.maternite.sante.model.Planning;

@Mapper(componentModel = "spring", uses = {AffectationMapper.class})
public interface PlanningMapper {

    PlanningResponseDto toDto(Planning planning);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "affectations", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Planning toEntity(PlanningRequestDto dto);
}
