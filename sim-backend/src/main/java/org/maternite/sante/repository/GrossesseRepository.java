package org.maternite.sante.repository;

import org.maternite.sante.model.Grossesse;
import org.maternite.sante.model.enums.EtatGrossesse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GrossesseRepository extends JpaRepository<Grossesse, Long> {

    List<Grossesse> findByPatienteId(Long patienteId);

    List<Grossesse> findByEtatGrossesse(EtatGrossesse etatGrossesse);

    @Query("SELECT g FROM Grossesse g WHERE g.patiente.id = :patienteId AND g.etatGrossesse = 'EN_COURS'")
    List<Grossesse> findGrossessesEnCoursByPatiente(@Param("patienteId") Long patienteId);

    @Query("SELECT g FROM Grossesse g WHERE g.datePrevue BETWEEN :startDate AND :endDate " +
            "AND g.etatGrossesse = 'EN_COURS'")
    List<Grossesse> findGrossessesAvecDatePrevueBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT g FROM Grossesse g WHERE g.datePrevue <= :date AND g.etatGrossesse = 'EN_COURS'")
    List<Grossesse> findGrossessesATerme(@Param("date") LocalDate date);

    @Query("SELECT COUNT(g) FROM Grossesse g WHERE g.etatGrossesse = :etat")
    Long countByEtat(@Param("etat") EtatGrossesse etat);

    @Query("SELECT g FROM Grossesse g LEFT JOIN FETCH g.patiente WHERE g.id = :id")
    Grossesse findByIdWithPatiente(@Param("id") Long id);
}