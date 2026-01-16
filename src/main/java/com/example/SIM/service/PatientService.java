package com.example.SIM.service;

import com.example.SIM.entity.DossierPatient;
import com.example.SIM.entity.Patient;
import com.example.SIM.repository.DossierPatientRepository;
import com.example.SIM.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Auteur : Coumba
 * Date : 16/01/2026
 */

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final DossierPatientRepository dossierPatientRepository;

    public PatientService(PatientRepository patientRepository ,DossierPatientRepository dossierPatientRepository){
        this.patientRepository = patientRepository;
        this.dossierPatientRepository = dossierPatientRepository;
    }
    public Patient creerPatient(Patient patient){
        Patient savedPatient = patientRepository.save(patient);
        DossierPatient dossier = new DossierPatient();
        dossier.setPatient(savedPatient);
        dossier.setDateCreation(LocalDate.now());
        dossier.setStatut("ACTIF");

        dossierPatientRepository.save(dossier);

        return savedPatient;

    }
    public Patient getPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient introuvable"));
    }

}
