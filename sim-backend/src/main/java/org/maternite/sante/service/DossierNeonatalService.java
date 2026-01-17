package org.maternite.sante.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maternite.sante.dto.request.DossierNeonatalRequestDto;
import org.maternite.sante.dto.response.DossierNeonatalResponseDto;
import org.maternite.sante.exceptions.BadRequestException;
import org.maternite.sante.exceptions.ResourceNotFoundException;
import org.maternite.sante.mapper.DossierNeonatalMapper;
import org.maternite.sante.model.DossierNeonatal;
import org.maternite.sante.model.NouveauNe;
import org.maternite.sante.repository.DossierNeonatalRepository;
import org.maternite.sante.repository.NouveauNeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DossierNeonatalService {

    private final DossierNeonatalRepository dossierNeonatalRepository;
    private final NouveauNeRepository nouveauNeRepository;
    private final DossierNeonatalMapper dossierNeonatalMapper;

    /**
     * Créer un dossier néonatal pour un nouveau-né
     */
    public DossierNeonatalResponseDto createDossierNeonatal(Long nouveauNeId, DossierNeonatalRequestDto dto) {

        NouveauNe nouveauNe = nouveauNeRepository.findById(nouveauNeId)
                .orElseThrow(() -> new ResourceNotFoundException("NouveauNe", "id", nouveauNeId));

        // Vérifier si un dossier existe déjà
        if (dossierNeonatalRepository.existsByNouveauNeId(nouveauNeId)) {
            throw new BadRequestException("Un dossier néonatal existe déjà pour ce nouveau-né");
        }

        DossierNeonatal dossier = dossierNeonatalMapper.toEntity(dto);
        dossier.setNouveauNe(nouveauNe);
        if (dossier.getDateCreation() == null) {
            dossier.setDateCreation(LocalDateTime.now());
        }

        DossierNeonatal saved = dossierNeonatalRepository.save(dossier);

        return dossierNeonatalMapper.toDto(saved);
    }

    /**
     * Récupérer le dossier néonatal d'un nouveau-né
     */
    @Transactional(readOnly = true)
    public DossierNeonatalResponseDto getDossierByNouveauNe(Long nouveauNeId) {

        DossierNeonatal dossier = dossierNeonatalRepository.findByNouveauNeId(nouveauNeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Dossier néonatal non trouvé pour le nouveau-né avec l'ID : " + nouveauNeId));

        return dossierNeonatalMapper.toDto(dossier);
    }

    /**
     * Récupérer un dossier néonatal par ID
     */
    @Transactional(readOnly = true)
    public DossierNeonatalResponseDto getDossierById(Long id) {

        DossierNeonatal dossier = dossierNeonatalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dossier néonatal", "id", id));

        return dossierNeonatalMapper.toDto(dossier);
    }

    /**
     * Mettre à jour un dossier néonatal
     */
    public DossierNeonatalResponseDto updateDossierNeonatal(Long id, DossierNeonatalRequestDto dto) {

        DossierNeonatal dossier = dossierNeonatalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dossier néonatal", "id", id));

        dossierNeonatalMapper.updateEntityFromDto(dto, dossier);
        dossier.setDateModification(LocalDateTime.now());

        DossierNeonatal updated = dossierNeonatalRepository.save(dossier);

        return dossierNeonatalMapper.toDto(updated);
    }

    /**
     * Mettre à jour le dossier néonatal d'un nouveau-né
     */
    public DossierNeonatalResponseDto updateDossierByNouveauNe(Long nouveauNeId, DossierNeonatalRequestDto dto) {

        DossierNeonatal dossier = dossierNeonatalRepository.findByNouveauNeId(nouveauNeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Dossier néonatal non trouvé pour le nouveau-né avec l'ID : " + nouveauNeId));

        dossierNeonatalMapper.updateEntityFromDto(dto, dossier);
        dossier.setDateModification(LocalDateTime.now());

        DossierNeonatal updated = dossierNeonatalRepository.save(dossier);

        return dossierNeonatalMapper.toDto(updated);
    }
}
