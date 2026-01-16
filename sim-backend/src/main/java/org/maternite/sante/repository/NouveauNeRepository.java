package org.maternite.sante.repository;

import org.maternite.sante.model.NouveauNe;
import org.maternite.sante.model.enums.Sexe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NouveauNeRepository extends JpaRepository<NouveauNe, Long> {

    List<NouveauNe> findByAccouchementId(Long accouchementId);

    List<NouveauNe> findBySexe(Sexe sexe);

    List<NouveauNe> findByDateNaissance(LocalDate dateNaissance);

    @Query("SELECT n FROM NouveauNe n WHERE n.dateNaissance BETWEEN :startDate AND :endDate")
    List<NouveauNe> findByDateNaissanceBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT COUNT(n) FROM NouveauNe n WHERE n.dateNaissance = :date")
    Long countNaissancesParDate(@Param("date") LocalDate date);

    @Query("SELECT COUNT(n) FROM NouveauNe n WHERE n.dateNaissance BETWEEN :startDate AND :endDate")
    Long countNaissancesBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT n FROM NouveauNe n WHERE n.poidsNaissance < :poids")
    List<NouveauNe> findByPoidsInferieur(@Param("poids") Double poids);

    @Query("SELECT n FROM NouveauNe n LEFT JOIN FETCH n.dossierNeonatal WHERE n.id = :id")
    NouveauNe findByIdWithDossier(@Param("id") Long id);
}
