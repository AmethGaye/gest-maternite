package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.request.ConsultationRequestDto;
import org.maternite.sante.dto.response.ConsultationResponseDto;
import org.maternite.sante.model.Consultation;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    @Mapping(source = "patiente.id", target = "patienteId")
    @Mapping(source = "patiente.nom", target = "patienteNom")
    @Mapping(source = "patiente.prenom", target = "patientePrenom")
    @Mapping(source = "nouveauNe.id", target = "nouveauNeId")
    @Mapping(source = "nouveauNe.nom", target = "nouveauNeNom")
    @Mapping(source = "nouveauNe.prenom", target = "nouveauNePrenom")
    @Mapping(source = "personnel.id", target = "utilisateurId")
    @Mapping(source = "personnel.nom", target = "personnelNom")
    @Mapping(source = "personnel.prenom", target = "personnelPrenom")
    ConsultationResponseDto toDto(Consultation consultation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patiente", ignore = true)
    @Mapping(target = "nouveauNe", ignore = true)
    @Mapping(target = "personnel", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Consultation toEntity(ConsultationRequestDto dto);
}