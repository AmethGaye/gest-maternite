package org.maternite.sante.service;

import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.PrescriptionRequestDto;
import org.maternite.sante.dto.response.PrescriptionResponseDto;
import org.maternite.sante.exceptions.ResourceNotFoundException;
import org.maternite.sante.mapper.MedicamentMapper;
import org.maternite.sante.mapper.PrescriptionMapper;
import org.maternite.sante.model.Medicament;
import org.maternite.sante.model.Patiente;
import org.maternite.sante.model.Prescription;
import org.maternite.sante.model.Utilisateur;
import org.maternite.sante.repository.PatienteRepository;
import org.maternite.sante.repository.PrescriptionRepository;
import org.maternite.sante.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PatienteRepository patienteRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PrescriptionMapper prescriptionMapper;
    private final MedicamentMapper medicamentMapper;

    @Transactional
    public PrescriptionResponseDto createPrescription(PrescriptionRequestDto dto) {
        Patiente patiente = patienteRepository.findById(dto.getPatienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Patiente non trouvée avec l'ID: " + dto.getPatienteId()));

        Utilisateur personnel = utilisateurRepository.findById(dto.getUtilisateurId())
                .orElseThrow(() -> new ResourceNotFoundException("Personnel non trouvé avec l'ID: " + dto.getUtilisateurId()));

        Prescription prescription = prescriptionMapper.toEntity(dto);
        prescription.setPatiente(patiente);
        prescription.setPersonnel(personnel);

        if (dto.getMedicaments() != null) {
            List<Medicament> medicaments = dto.getMedicaments().stream()
                    .map(medicamentDto -> {
                        Medicament medicament = medicamentMapper.toEntity(medicamentDto);
                        medicament.setPrescription(prescription);
                        return medicament;
                    })
                    .collect(Collectors.toList());
            prescription.setMedicaments(medicaments);
        }

        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return prescriptionMapper.toDto(savedPrescription);
    }

    public List<PrescriptionResponseDto> getAllPrescriptions() {
        return prescriptionRepository.findAll().stream()
                .map(prescriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    public PrescriptionResponseDto getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription non trouvée avec l'ID: " + id));
        return prescriptionMapper.toDto(prescription);
    }

    public List<PrescriptionResponseDto> getPrescriptionsByPatiente(Long patienteId) {
        return prescriptionRepository.findByPatienteId(patienteId).stream()
                .map(prescriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PrescriptionResponseDto> getPrescriptionsByPersonnel(Long personnelId) {
        return prescriptionRepository.findByPersonnelId(personnelId).stream()
                .map(prescriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePrescription(Long id) {
        if (!prescriptionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prescription non trouvée avec l'ID: " + id);
        }
        prescriptionRepository.deleteById(id);
    }
}
