package org.maternite.sante.mapper;

import org.mapstruct.*;
import org.maternite.sante.dto.request.PrescriptionRequestDto;
import org.maternite.sante.dto.response.PrescriptionResponseDto;
import org.maternite.sante.model.Prescription;

@Mapper(componentModel = "spring", uses = {MedicamentMapper.class})
public interface PrescriptionMapper {

    @Mapping(source = "personnel.id", target = "utilisateurId")
    @Mapping(source = "personnel.nom", target = "personnelNom")
    @Mapping(source = "personnel.prenom", target = "personnelPrenom")
    @Mapping(source = "patiente.id", target = "patienteId")
    @Mapping(source = "patiente.nom", target = "patienteNom")
    @Mapping(source = "patiente.prenom", target = "patientePrenom")
    PrescriptionResponseDto toDto(Prescription prescription);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "personnel", ignore = true)
    @Mapping(target = "patiente", ignore = true)
    @Mapping(target = "medicaments", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Prescription toEntity(PrescriptionRequestDto dto);
}
