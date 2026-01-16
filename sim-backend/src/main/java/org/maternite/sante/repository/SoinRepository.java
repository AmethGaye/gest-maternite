package org.maternite.sante.repository;

import org.maternite.sante.model.Soin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SoinRepository extends JpaRepository<Soin, Long> {

    List<Soin> findByNouveauNeId(Long nouveauNeId);

    List<Soin> findByCreatedAt(LocalDateTime createdAt);

    List<Soin> findByTypeSoin(String typeSoin);

    @Query("SELECT s FROM Soin s WHERE s.createdAt BETWEEN :startDate AND :endDate")
    List<Soin> findByDateBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT s FROM Soin s WHERE s.nouveauNe.id = :nouveauNeId " +
            "ORDER BY s.createdAt DESC")
    List<Soin> findSoinsByNouveauNeOrderByDateDesc(@Param("nouveauNeId") Long nouveauNeId);

    @Query("SELECT COUNT(s) FROM Soin s WHERE s.createdAt = :date")
    Long countSoinsParDate(@Param("date") LocalDateTime date);

    @Query("SELECT s FROM Soin s LEFT JOIN FETCH s.nouveauNe WHERE s.id = :id")
    Soin findByIdWithNouveauNe(@Param("id") Long id);
}
