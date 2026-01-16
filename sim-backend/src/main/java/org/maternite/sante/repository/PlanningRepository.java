package org.maternite.sante.repository;

import org.maternite.sante.model.Planning;
import org.maternite.sante.model.enums.StatutPlanning;
import org.maternite.sante.model.enums.TypeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PlanningRepository extends JpaRepository<Planning, Long> {

    List<Planning> findByCreatedAt(LocalDateTime createdAt);

    List<Planning> findByStatut(StatutPlanning statut);

    List<Planning> findByTypeService(TypeService typeService);

    @Query("SELECT p FROM Planning p WHERE p.createdAt BETWEEN :startDate AND :endDate")
    List<Planning> findByDateBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT p FROM Planning p WHERE p.createdAt = :date AND p.typeService = :typeService")
    List<Planning> findByDateAndTypeService(
            @Param("date") LocalDateTime date,
            @Param("typeService") TypeService typeService
    );

    @Query("SELECT p FROM Planning p WHERE p.createdAt >= :date AND p.statut = 'PLANIFIE'")
    List<Planning> findPlanningsFuturs(@Param("date") LocalDateTime date);

    @Query("SELECT p FROM Planning p LEFT JOIN FETCH p.affectations WHERE p.id = :id")
    Planning findByIdWithAffectations(@Param("id") Long id);
}
