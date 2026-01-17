package org.maternite.sante.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maternite.sante.dto.request.DossierMedicalRequestDto;
import org.maternite.sante.dto.response.DossierMedicalResponseDto;
import org.maternite.sante.exceptions.BadRequestException;
import org.maternite.sante.exceptions.ResourceNotFoundException;
import org.maternite.sante.mapper.DossierMedicalMapper;
import org.maternite.sante.model.DossierMedical;
import org.maternite.sante.model.Patiente;
import org.maternite.sante.repository.DossierMedicalRepository;
import org.maternite.sante.repository.PatienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DossierMedicalService {

    private final DossierMedicalRepository dossierMedicalRepository;
    private final PatienteRepository patienteRepository;
    private final DossierMedicalMapper dossierMedicalMapper;

    /**
     * Créer un dossier médical pour une patiente
     */
    public DossierMedicalResponseDto createDossierMedical(Long patienteId, DossierMedicalRequestDto dto) {

        Patiente patiente = patienteRepository.findById(patienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Patiente", "id", patienteId));

        // Vérifier si un dossier existe déjà
        if (dossierMedicalRepository.existsByPatienteId(patienteId)) {
            throw new BadRequestException("Un dossier médical existe déjà pour cette patiente");
        }

        DossierMedical dossier = dossierMedicalMapper.toEntity(dto);
        dossier.setPatiente(patiente);

        DossierMedical saved = dossierMedicalRepository.save(dossier);

        return dossierMedicalMapper.toDto(saved);
    }

    /**
     * Récupérer le dossier médical d'une patiente
     */
    @Transactional(readOnly = true)
    public DossierMedicalResponseDto getDossierByPatiente(Long patienteId) {

        DossierMedical dossier = dossierMedicalRepository.findByPatienteId(patienteId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Dossier médical non trouvé pour la patiente avec l'ID : " + patienteId));

        return dossierMedicalMapper.toDto(dossier);
    }

    /**
     * Récupérer un dossier médical par ID
     */
    @Transactional(readOnly = true)
    public DossierMedicalResponseDto getDossierById(Long id) {

        DossierMedical dossier = dossierMedicalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dossier médical", "id", id));

        return dossierMedicalMapper.toDto(dossier);
    }

    /**
     * Mettre à jour un dossier médical
     */
    public DossierMedicalResponseDto updateDossierMedical(Long id, DossierMedicalRequestDto dto) {

        DossierMedical dossier = dossierMedicalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dossier médical", "id", id));

        dossierMedicalMapper.updateEntityFromDto(dto, dossier);

        DossierMedical updated = dossierMedicalRepository.save(dossier);

        return dossierMedicalMapper.toDto(updated);
    }

    /**
     * Mettre à jour le dossier médical d'une patiente
     */
    public DossierMedicalResponseDto updateDossierByPatiente(Long patienteId, DossierMedicalRequestDto dto) {

        DossierMedical dossier = dossierMedicalRepository.findByPatienteId(patienteId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Dossier médical non trouvé pour la patiente avec l'ID : " + patienteId));

        dossierMedicalMapper.updateEntityFromDto(dto, dossier);

        DossierMedical updated = dossierMedicalRepository.save(dossier);

        return dossierMedicalMapper.toDto(updated);
    }

}