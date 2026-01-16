package org.maternite.sante.repository;

import org.maternite.sante.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findByPatienteId(Long patienteId);

    List<Prescription> findByPersonnelId(Long personnelId);

    List<Prescription> findByCreatedAt(LocalDateTime date);

    @Query("SELECT p FROM Prescription p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    List<Prescription> findByDateBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT p FROM Prescription p WHERE p.patiente.id = :patienteId " +
            "ORDER BY p.createdAt DESC")
    List<Prescription> findPrescriptionsByPatienteOrderByDateDesc(@Param("patienteId") Long patienteId);

    @Query("SELECT p FROM Prescription p WHERE p.personnel.id = :personnelId " +
            "AND p.createdAt = :date")
    List<Prescription> findByPersonnelAndDate(
            @Param("personnelId") Long personnelId,
            @Param("date") LocalDateTime date
    );

    @Query("SELECT COUNT(p) FROM Prescription p WHERE p.createdAt = :date")
    Long countPrescriptionsParDate(@Param("date") LocalDateTime date);

    @Query("SELECT p FROM Prescription p LEFT JOIN FETCH p.medicaments WHERE p.id = :id")
    Prescription findByIdWithMedicaments(@Param("id") Long id);
}
