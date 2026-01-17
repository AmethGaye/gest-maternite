package org.maternite.sante.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maternite.sante.dto.request.PatienteRequestDto;
import org.maternite.sante.dto.response.PatienteResponseDto;
import org.maternite.sante.exceptions.ResourceNotFoundException;
import org.maternite.sante.mapper.PatienteMapper;
import org.maternite.sante.model.DossierMedical;
import org.maternite.sante.model.Patiente;
import org.maternite.sante.repository.PatienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PatienteService {

    private final PatienteRepository patienteRepository;
    private final PatienteMapper patienteMapper;

    /**
     * Créer une nouvelle patiente
     */
    public PatienteResponseDto createPatiente(PatienteRequestDto dto) {

        Patiente patiente = patienteMapper.toEntity(dto);

        // Créer automatiquement un dossier médical vide
        DossierMedical dossierMedical = DossierMedical.builder()
                .patiente(patiente)
                .build();
        patiente.setDossierMedical(dossierMedical);

        Patiente saved = patienteRepository.save(patiente);

        return patienteMapper.toDto(saved);
    }

    /**
     * Récupérer toutes les patientes
     */
    @Transactional(readOnly = true)
    public List<PatienteResponseDto> getAllPatientes() {
        return patienteRepository.findAll()
                .stream()
                .map(patienteMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les patientes avec pagination
     */
    @Transactional(readOnly = true)
    public Page<PatienteResponseDto> getAllPatientes(Pageable pageable) {

        return patienteRepository.findAll(pageable)
                .map(patienteMapper::toDto);
    }

    /**
     * Récupérer une patiente par ID
     */
    @Transactional(readOnly = true)
    public PatienteResponseDto getPatienteById(Long id) {
        Patiente patiente = patienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patiente", "id", id));
        return patienteMapper.toDto(patiente);
    }

    /**
     * Mettre à jour une patiente
     */
    public PatienteResponseDto updatePatiente(Long id, PatienteRequestDto dto) {

        Patiente patiente = patienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patiente", "id", id));

        patienteMapper.updateEntityFromDto(dto, patiente);

        Patiente updated = patienteRepository.save(patiente);

        return patienteMapper.toDto(updated);
    }

    /**
     * Supprimer une patiente
     */
    public void deletePatiente(Long id) {

        if (!patienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patiente", "id", id);
        }

        patienteRepository.deleteById(id);
    }

    /**
     * Rechercher des patientes par nom ou prénom
     */
    @Transactional(readOnly = true)
    public List<PatienteResponseDto> searchPatientes(String keyword) {
        return patienteRepository
                .findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(keyword, keyword)
                .stream()
                .map(patienteMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer une patiente avec son dossier médical
     */
    @Transactional(readOnly = true)
    public PatienteResponseDto getPatienteWithDossier(Long id) {
        Patiente patiente = patienteRepository.findByIdWithDossier(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patiente", "id", id));
        return patienteMapper.toDto(patiente);
    }

    /**
     * Compter le nombre de nouvelles patientes depuis une date
     */
    @Transactional(readOnly = true)
    public Long countNewPatientesSince(java.time.LocalDate date) {
        return patienteRepository.countNewPatientesSince(date);
    }
}
