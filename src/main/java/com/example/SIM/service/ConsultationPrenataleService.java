package com.example.SIM.service;

import com.example.SIM.entity.ConsultationPrenatale;
import com.example.SIM.entity.DossierPatient;
import com.example.SIM.repository.ConsultationPrenataleRepository;
import com.example.SIM.repository.DossierPatientRepository;
import com.example.SIM.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


/**
 * Auteur : Coumba
 * Date : 16/01/2026
 */
@Service
public class ConsultationPrenataleService {
    private final ConsultationPrenataleRepository consultationRepository;
    private final DossierPatientRepository dossierRepository;

    public ConsultationPrenataleService(ConsultationPrenataleRepository consultationRepository, DossierPatientRepository dossierRepository){
        this.consultationRepository = consultationRepository;
        this.dossierRepository = dossierRepository;
    }
    public ConsultationPrenatale ajouterConsultation(
            Long patientId, ConsultationPrenatale consultation) {

        DossierPatient dossier = dossierRepository.findByPatientId(patientId)
                .orElseThrow(() -> new RuntimeException("Dossier patient introuvable"));

        consultation.setDossier(dossier);
        consultation.setDateConsultation(LocalDate.now());

        return consultationRepository.save(consultation);
    }

    public List<ConsultationPrenatale> getConsultations(Long dossierId) {
        return consultationRepository.findByDossierId(dossierId);
    }
}
