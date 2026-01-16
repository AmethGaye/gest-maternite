package com.example.SIM.repository;

import com.example.SIM.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Auteur : Coumba
 * Date : 16/01/2026
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
