package com.example.SIM.repository;

import com.example.SIM.entity.ConsultationPrenatale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Auteur : Coumba
 * Date : 16/01/2026
 */
public interface ConsultationPrenataleRepository extends JpaRepository<ConsultationPrenatale, Long> {
    List<ConsultationPrenatale> findByDossierId(Long dossierId);
}
