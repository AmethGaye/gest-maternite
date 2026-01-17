package org.maternite.sante.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maternite.sante.dto.request.NouveauNeRequestDto;
import org.maternite.sante.dto.response.NouveauNeResponseDto;
import org.maternite.sante.exceptions.ResourceNotFoundException;
import org.maternite.sante.mapper.NouveauNeMapper;
import org.maternite.sante.model.Accouchement;
import org.maternite.sante.model.DossierNeonatal;
import org.maternite.sante.model.NouveauNe;
import org.maternite.sante.repository.AccouchementRepository;
import org.maternite.sante.repository.NouveauNeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NouveauNeService {

    private final NouveauNeRepository nouveauNeRepository;
    private final AccouchementRepository accouchementRepository;
    private final NouveauNeMapper nouveauNeMapper;

    public NouveauNeResponseDto createNouveauNe(NouveauNeRequestDto dto) {
        log.info("Création d'un nouveau-né pour l'accouchement ID: {}", dto.getAccouchementId());

        Accouchement accouchement = accouchementRepository.findById(dto.getAccouchementId())
                .orElseThrow(() -> new ResourceNotFoundException("Accouchement", "id", dto.getAccouchementId()));

        NouveauNe nouveauNe = nouveauNeMapper.toEntity(dto);
        nouveauNe.setAccouchement(accouchement);

        // Création automatique du dossier néonatal
        DossierNeonatal dossierNeonatal = DossierNeonatal.builder()
                .nouveauNe(nouveauNe)
                .dateCreation(LocalDateTime.now())
                .reanimation(false)
                .detresseRespiratoire(false)
                .testGuthrie(false)
                .depistage(false)
                .build();
        nouveauNe.setDossierNeonatal(dossierNeonatal);

        NouveauNe saved = nouveauNeRepository.save(nouveauNe);
        return nouveauNeMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<NouveauNeResponseDto> getAllNouveauNes() {
        return nouveauNeRepository.findAll().stream()
                .map(nouveauNeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NouveauNeResponseDto getNouveauNeById(Long id) {
        NouveauNe nouveauNe = nouveauNeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NouveauNe", "id", id));
        return nouveauNeMapper.toDto(nouveauNe);
    }

    @Transactional(readOnly = true)
    public List<NouveauNeResponseDto> getNouveauNesByAccouchementId(Long accouchementId) {
        return nouveauNeRepository.findByAccouchementId(accouchementId).stream()
                .map(nouveauNeMapper::toDto)
                .collect(Collectors.toList());
    }

    public NouveauNeResponseDto updateNouveauNe(Long id, NouveauNeRequestDto dto) {
        NouveauNe nouveauNe = nouveauNeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NouveauNe", "id", id));

        nouveauNe.setNom(dto.getNom());
        nouveauNe.setPrenom(dto.getPrenom());
        nouveauNe.setSexe(dto.getSexe());
        nouveauNe.setPoidsNaissance(dto.getPoidsNaissance());
        nouveauNe.setTailleNaissance(dto.getTailleNaissance());
        nouveauNe.setDateNaissance(dto.getDateNaissance());

        NouveauNe updated = nouveauNeRepository.save(nouveauNe);
        return nouveauNeMapper.toDto(updated);
    }

    public void deleteNouveauNe(Long id) {
        if (!nouveauNeRepository.existsById(id)) {
            throw new ResourceNotFoundException("NouveauNe", "id", id);
        }
        nouveauNeRepository.deleteById(id);
    }
}
