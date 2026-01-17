package org.maternite.sante.service;

import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.PlanningRequestDto;
import org.maternite.sante.dto.response.PlanningResponseDto;
import org.maternite.sante.exceptions.ResourceNotFoundException;
import org.maternite.sante.mapper.PlanningMapper;
import org.maternite.sante.model.Planning;
import org.maternite.sante.repository.PlanningRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanningService {

    private final PlanningRepository planningRepository;
    private final PlanningMapper planningMapper;

    @Transactional
    public PlanningResponseDto createPlanning(PlanningRequestDto dto) {
        Planning planning = planningMapper.toEntity(dto);
        Planning savedPlanning = planningRepository.save(planning);
        return planningMapper.toDto(savedPlanning);
    }

    public List<PlanningResponseDto> getAllPlannings() {
        return planningRepository.findAll().stream()
                .map(planningMapper::toDto)
                .collect(Collectors.toList());
    }

    public PlanningResponseDto getPlanningById(Long id) {
        Planning planning = planningRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planning non trouvé avec l'ID: " + id));
        return planningMapper.toDto(planning);
    }

    @Transactional
    public PlanningResponseDto updatePlanning(Long id, PlanningRequestDto dto) {
        Planning planning = planningRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planning non trouvé avec l'ID: " + id));

        planning.setHeureDebut(dto.getHeureDebut());
        planning.setHeureFin(dto.getHeureFin());
        planning.setTypeService(dto.getTypeService());
        planning.setStatut(dto.getStatut());

        Planning updatedPlanning = planningRepository.save(planning);
        return planningMapper.toDto(updatedPlanning);
    }

    @Transactional
    public void deletePlanning(Long id) {
        if (!planningRepository.existsById(id)) {
            throw new ResourceNotFoundException("Planning non trouvé avec l'ID: " + id);
        }
        planningRepository.deleteById(id);
    }
}
