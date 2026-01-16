package org.maternite.sante.repository;

import org.maternite.sante.model.Accouchement;
import org.maternite.sante.model.enums.TypeAccouchement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccouchementRepository extends JpaRepository<Accouchement, Long> {

    Optional<Accouchement> findByGrossesseId(Long grossesseId);

    List<Accouchement> findByCreatedAt(LocalDateTime date);

    List<Accouchement> findByType(TypeAccouchement type);

    List<Accouchement> findBySalleId(Long salleId);

    @Query("SELECT a FROM Accouchement a WHERE a.createdAt BETWEEN :startDate AND :endDate")
    List<Accouchement> findByDateBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT a FROM Accouchement a WHERE a.createdAt = :date AND a.salle.id = :salleId")
    List<Accouchement> findByDateAndSalle(
            @Param("date") LocalDate date,
            @Param("salleId") Long salleId
    );

    @Query("SELECT COUNT(a) FROM Accouchement a WHERE a.createdAt = :date")
    Long countAccouchementsParDate(@Param("date") LocalDate date);

    @Query("SELECT COUNT(a) FROM Accouchement a WHERE a.type = :type AND a.createdAt BETWEEN :startDate AND :endDate")
    Long countByTypeAndDateBetween(
            @Param("type") TypeAccouchement type,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT a FROM Accouchement a LEFT JOIN FETCH a.grossesse g LEFT JOIN FETCH g.patiente WHERE a.id = :id")
    Optional<Accouchement> findByIdWithDetails(@Param("id") Long id);
}
