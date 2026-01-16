package org.maternite.sante.repository;

import org.maternite.sante.model.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation, Long> {

    List<Affectation> findByPlanningId(Long planningId);

    List<Affectation> findByPersonnelId(Long personnelId);

    List<Affectation> findBySalleId(Long salleId);

    List<Affectation> findByCreatedAt(LocalDateTime createdAt);

    @Query("SELECT a FROM Affectation a WHERE a.personnel.id = :personnelId AND a.createdAt = :date")
    List<Affectation> findByPersonnelAndDate(
            @Param("personnelId") Long personnelId,
            @Param("date") LocalDate date
    );

    @Query("SELECT a FROM Affectation a WHERE a.salle.id = :salleId AND a.createdAt = :date")
    List<Affectation> findBySalleAndDate(
            @Param("salleId") Long salleId,
            @Param("date") LocalDate date
    );

    @Query("SELECT a FROM Affectation a WHERE a.createdAt BETWEEN :startDate AND :endDate " +
            "AND a.personnel.id = :personnelId")
    List<Affectation> findAffectationsPersonnelBetween(
            @Param("personnelId") Long personnelId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT a FROM Affectation a LEFT JOIN FETCH a.planning " +
            "LEFT JOIN FETCH a.personnel LEFT JOIN FETCH a.salle WHERE a.id = :id")
    Affectation findByIdWithDetails(@Param("id") Long id);
}
