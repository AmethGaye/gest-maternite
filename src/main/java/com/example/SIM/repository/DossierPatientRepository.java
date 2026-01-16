package com.example.SIM.repository;

import com.example.SIM.entity.ConsultationPrenatale;
import com.example.SIM.entity.DossierPatient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Auteur : Coumba
 * Date : 16/01/2026
 */
public interface DossierPatientRepository extends JpaRepository<DossierPatient, Long> {
    Optional<DossierPatient> findByPatientId(Long patientId);
}
