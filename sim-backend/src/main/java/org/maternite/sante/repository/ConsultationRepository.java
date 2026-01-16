package org.maternite.sante.repository;

import org.maternite.sante.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    List<Consultation> findByPatienteId(Long patienteId);

    List<Consultation> findByNouveauNeId(Long nouveauNeId);

    List<Consultation> findByPersonnelId(Long personnelId);

    List<Consultation> findByDateConsultation(LocalDate date);

    @Query("SELECT c FROM Consultation c WHERE c.dateConsultation BETWEEN :startDate AND :endDate")
    List<Consultation> findByDateConsultationBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT c FROM Consultation c WHERE c.patiente.id = :patienteId " +
            "ORDER BY c.dateConsultation DESC")
    List<Consultation> findConsultationsByPatienteOrderByDateDesc(@Param("patienteId") Long patienteId);

    @Query("SELECT c FROM Consultation c WHERE c.nouveauNe.id = :nouveauNeId " +
            "ORDER BY c.dateConsultation DESC")
    List<Consultation> findConsultationsByNouveauNeOrderByDateDesc(@Param("nouveauNeId") Long nouveauNeId);

    @Query("SELECT COUNT(c) FROM Consultation c WHERE c.personnel.id = :personnelId " +
            "AND c.dateConsultation = :date")
    Long countConsultationsParPersonnelEtDate(
            @Param("personnelId") Long personnelId,
            @Param("date") LocalDate date
    );

    @Query("SELECT c FROM Consultation c LEFT JOIN FETCH c.patiente " +
            "LEFT JOIN FETCH c.personnel WHERE c.id = :id")
    Consultation findByIdWithDetails(@Param("id") Long id);
}
