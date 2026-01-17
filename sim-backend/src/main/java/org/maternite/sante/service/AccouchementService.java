package org.maternite.sante.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maternite.sante.dto.request.AccouchementRequestDto;
import org.maternite.sante.dto.response.AccouchementResponseDto;
import org.maternite.sante.exceptions.ResourceNotFoundException;
import org.maternite.sante.mapper.AccouchementMapper;
import org.maternite.sante.model.Accouchement;
import org.maternite.sante.model.Grossesse;
import org.maternite.sante.model.SalleAccouchement;
import org.maternite.sante.repository.AccouchementRepository;
import org.maternite.sante.repository.GrossesseRepository;
import org.maternite.sante.repository.SalleAccouchementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AccouchementService {

    private final AccouchementRepository accouchementRepository;
    private final GrossesseRepository grossesseRepository;
    private final SalleAccouchementRepository salleAccouchementRepository;
    private final AccouchementMapper accouchementMapper;

    public AccouchementResponseDto createAccouchement(AccouchementRequestDto dto) {
        log.info("Création d'un nouvel accouchement pour la grossesse ID: {}", dto.getGrossesseId());

        Grossesse grossesse = grossesseRepository.findById(dto.getGrossesseId())
                .orElseThrow(() -> new ResourceNotFoundException("Grossesse", "id", dto.getGrossesseId()));

        Accouchement accouchement = accouchementMapper.toEntity(dto);
        accouchement.setGrossesse(grossesse);

        if (dto.getSalleId() != null) {
            SalleAccouchement salle = salleAccouchementRepository.findById(dto.getSalleId())
                    .orElseThrow(() -> new ResourceNotFoundException("SalleAccouchement", "id", dto.getSalleId()));
            accouchement.setSalle(salle);
        }

        Accouchement saved = accouchementRepository.save(accouchement);
        return accouchementMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<AccouchementResponseDto> getAllAccouchements() {
        return accouchementRepository.findAll().stream()
                .map(accouchementMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AccouchementResponseDto getAccouchementById(Long id) {
        Accouchement accouchement = accouchementRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accouchement", "id", id));
        return accouchementMapper.toDto(accouchement);
    }

    public AccouchementResponseDto updateAccouchement(Long id, AccouchementRequestDto dto) {
        Accouchement accouchement = accouchementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accouchement", "id", id));

        accouchement.setHeureDebut(dto.getHeureDebut());
        accouchement.setHeureFin(dto.getHeureFin());
        accouchement.setType(dto.getType());
        accouchement.setComplications(dto.getComplications());

        if (dto.getSalleId() != null) {
            SalleAccouchement salle = salleAccouchementRepository.findById(dto.getSalleId())
                    .orElseThrow(() -> new ResourceNotFoundException("SalleAccouchement", "id", dto.getSalleId()));
            accouchement.setSalle(salle);
        }

        Accouchement updated = accouchementRepository.save(accouchement);
        return accouchementMapper.toDto(updated);
    }

    public void deleteAccouchement(Long id) {
        if (!accouchementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Accouchement", "id", id);
        }
        accouchementRepository.deleteById(id);
    }
}
