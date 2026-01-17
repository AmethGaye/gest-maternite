package org.maternite.sante.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maternite.sante.dto.request.ConsultationRequestDto;
import org.maternite.sante.dto.response.ConsultationResponseDto;
import org.maternite.sante.exceptions.BadRequestException;
import org.maternite.sante.exceptions.ResourceNotFoundException;
import org.maternite.sante.mapper.ConsultationMapper;
import org.maternite.sante.model.Consultation;
import org.maternite.sante.model.NouveauNe;
import org.maternite.sante.model.Patiente;
import org.maternite.sante.model.Utilisateur;
import org.maternite.sante.repository.ConsultationRepository;
import org.maternite.sante.repository.NouveauNeRepository;
import org.maternite.sante.repository.PatienteRepository;
import org.maternite.sante.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final PatienteRepository patienteRepository;
    private final NouveauNeRepository nouveauNeRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ConsultationMapper consultationMapper;

    /**
     * Créer une nouvelle consultation
     */
    public ConsultationResponseDto createConsultation(ConsultationRequestDto dto) {

        // Validation : une consultation doit concerner soit une patiente, soit un nouveau-né
        if (dto.getPatienteId() == null && dto.getNouveauNeId() == null) {
            throw new BadRequestException("Une consultation doit concerner une patiente ou un nouveau-né");
        }

        if (dto.getPatienteId() != null && dto.getNouveauNeId() != null) {
            throw new BadRequestException("Une consultation ne peut pas concerner à la fois une patiente et un nouveau-né");
        }

        Consultation consultation = consultationMapper.toEntity(dto);

        // Associer le personnel
        Utilisateur personnel = utilisateurRepository.findById(dto.getUtilisateurId())
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", dto.getUtilisateurId()));
        consultation.setPersonnel(personnel);

        // Associer la patiente ou le nouveau-né
        if (dto.getPatienteId() != null) {
            Patiente patiente = patienteRepository.findById(dto.getPatienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patiente", "id", dto.getPatienteId()));
            consultation.setPatiente(patiente);
        } else {
            NouveauNe nouveauNe = nouveauNeRepository.findById(dto.getNouveauNeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Nouveau-né", "id", dto.getNouveauNeId()));
            consultation.setNouveauNe(nouveauNe);
        }

        Consultation saved = consultationRepository.save(consultation);

        return consultationMapper.toDto(saved);
    }

    /**
     * Récupérer toutes les consultations
     */
    @Transactional(readOnly = true)
    public List<ConsultationResponseDto> getAllConsultations() {
        return consultationRepository.findAll()
                .stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer une consultation par ID
     */
    @Transactional(readOnly = true)
    public ConsultationResponseDto getConsultationById(Long id) {

        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation", "id", id));

        return consultationMapper.toDto(consultation);
    }

    /**
     * Récupérer les consultations d'une patiente
     */
    @Transactional(readOnly = true)
    public List<ConsultationResponseDto> getConsultationsByPatiente(Long patienteId) {

        return consultationRepository.findConsultationsByPatienteOrderByDateDesc(patienteId)
                .stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les consultations d'un nouveau-né
     */
    @Transactional(readOnly = true)
    public List<ConsultationResponseDto> getConsultationsByNouveauNe(Long nouveauNeId) {

        return consultationRepository.findConsultationsByNouveauNeOrderByDateDesc(nouveauNeId)
                .stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les consultations d'un personnel
     */
    @Transactional(readOnly = true)
    public List<ConsultationResponseDto> getConsultationsByPersonnel(Long personnelId) {

        return consultationRepository.findByPersonnelId(personnelId)
                .stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les consultations par date
     */
    @Transactional(readOnly = true)
    public List<ConsultationResponseDto> getConsultationsByDate(LocalDate date) {

        return consultationRepository.findByDateConsultation(date)
                .stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer les consultations entre deux dates
     */
    @Transactional(readOnly = true)
    public List<ConsultationResponseDto> getConsultationsBetweenDates(LocalDate startDate, LocalDate endDate) {

        return consultationRepository.findByDateConsultationBetween(startDate, endDate)
                .stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Mettre à jour une consultation
     */
    public ConsultationResponseDto updateConsultation(Long id, ConsultationRequestDto dto) {

        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation", "id", id));

        // Mise à jour des champs simples
        if (dto.getDateConsultation() != null) {
            consultation.setDateConsultation(dto.getDateConsultation());
        }
        if (dto.getPoids() != null) {
            consultation.setPoids(dto.getPoids());
        }
        if (dto.getTemperature() != null) {
            consultation.setTemperature(dto.getTemperature());
        }
        if (dto.getTensionArterielle() != null) {
            consultation.setTensionArterielle(dto.getTensionArterielle());
        }
        if (dto.getObservations() != null) {
            consultation.setObservations(dto.getObservations());
        }

        Consultation updated = consultationRepository.save(consultation);
        log.info("Consultation mise à jour avec succès : {}", id);

        return consultationMapper.toDto(updated);
    }

    /**
     * Supprimer une consultation
     */
    public void deleteConsultation(Long id) {

        if (!consultationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consultation", "id", id);
        }

        consultationRepository.deleteById(id);
        log.info("Consultation supprimée avec succès : {}", id);
    }

    /**
     * Compter les consultations d'un personnel pour une date donnée
     */
    @Transactional(readOnly = true)
    public Long countConsultationsByPersonnelAndDate(Long personnelId, LocalDate date) {
        return consultationRepository.countConsultationsParPersonnelEtDate(personnelId, date);
    }
}
