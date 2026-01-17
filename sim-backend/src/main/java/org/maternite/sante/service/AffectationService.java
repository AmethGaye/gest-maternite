package org.maternite.sante.service;

import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.AffectationRequestDto;
import org.maternite.sante.dto.response.AffectationResponseDto;
import org.maternite.sante.exceptions.ResourceNotFoundException;
import org.maternite.sante.mapper.AffectationMapper;
import org.maternite.sante.model.Affectation;
import org.maternite.sante.model.Planning;
import org.maternite.sante.model.SalleAccouchement;
import org.maternite.sante.model.Utilisateur;
import org.maternite.sante.repository.AffectationRepository;
import org.maternite.sante.repository.PlanningRepository;
import org.maternite.sante.repository.SalleAccouchementRepository;
import org.maternite.sante.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AffectationService {

    private final AffectationRepository affectationRepository;
    private final PlanningRepository planningRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final SalleAccouchementRepository salleAccouchementRepository;
    private final AffectationMapper affectationMapper;

    @Transactional
    public AffectationResponseDto createAffectation(AffectationRequestDto dto) {
        Planning planning = planningRepository.findById(dto.getPlanningId())
                .orElseThrow(() -> new ResourceNotFoundException("Planning non trouvé avec l'ID: " + dto.getPlanningId()));

        Utilisateur personnel = utilisateurRepository.findById(dto.getUtilisateurId())
                .orElseThrow(() -> new ResourceNotFoundException("Personnel non trouvé avec l'ID: " + dto.getUtilisateurId()));

        Affectation affectation = affectationMapper.toEntity(dto);
        affectation.setPlanning(planning);
        affectation.setPersonnel(personnel);

        if (dto.getSalleId() != null) {
            SalleAccouchement salle = salleAccouchementRepository.findById(dto.getSalleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Salle non trouvée avec l'ID: " + dto.getSalleId()));
            affectation.setSalle(salle);
        }

        Affectation savedAffectation = affectationRepository.save(affectation);
        return affectationMapper.toDto(savedAffectation);
    }

    public List<AffectationResponseDto> getAllAffectations() {
        return affectationRepository.findAll().stream()
                .map(affectationMapper::toDto)
                .collect(Collectors.toList());
    }

    public AffectationResponseDto getAffectationById(Long id) {
        Affectation affectation = affectationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Affectation non trouvée avec l'ID: " + id));
        return affectationMapper.toDto(affectation);
    }

    public List<AffectationResponseDto> getAffectationsByPersonnel(Long personnelId) {
        return affectationRepository.findByPersonnelId(personnelId).stream()
                .map(affectationMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AffectationResponseDto> getAffectationsByPlanning(Long planningId) {
        return affectationRepository.findByPlanningId(planningId).stream()
                .map(affectationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AffectationResponseDto updateAffectation(Long id, AffectationRequestDto dto) {
        Affectation affectation = affectationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Affectation non trouvée avec l'ID: " + id));

        Planning planning = planningRepository.findById(dto.getPlanningId())
                .orElseThrow(() -> new ResourceNotFoundException("Planning non trouvé avec l'ID: " + dto.getPlanningId()));

        Utilisateur personnel = utilisateurRepository.findById(dto.getUtilisateurId())
                .orElseThrow(() -> new ResourceNotFoundException("Personnel non trouvé avec l'ID: " + dto.getUtilisateurId()));

        affectation.setPlanning(planning);
        affectation.setPersonnel(personnel);
        affectation.setStatut(dto.getStatut());

        if (dto.getSalleId() != null) {
            SalleAccouchement salle = salleAccouchementRepository.findById(dto.getSalleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Salle non trouvée avec l'ID: " + dto.getSalleId()));
            affectation.setSalle(salle);
        } else {
            affectation.setSalle(null);
        }

        Affectation updatedAffectation = affectationRepository.save(affectation);
        return affectationMapper.toDto(updatedAffectation);
    }

    @Transactional
    public void deleteAffectation(Long id) {
        if (!affectationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Affectation non trouvée avec l'ID: " + id);
        }
        affectationRepository.deleteById(id);
    }
}
