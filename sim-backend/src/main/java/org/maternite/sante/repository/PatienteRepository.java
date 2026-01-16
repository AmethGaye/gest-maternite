package org.maternite.sante.repository;

import org.maternite.sante.model.Patiente;
import org.maternite.sante.model.enums.GroupeSanguin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatienteRepository extends JpaRepository<Patiente, Long> {

    List<Patiente> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);

    Optional<Patiente> findByTelephone(String telephone);

    List<Patiente> findByGroupeSanguin(GroupeSanguin groupeSanguin);

    @Query("SELECT p FROM Patiente p WHERE p.dateNaissance BETWEEN :startDate AND :endDate")
    List<Patiente> findByDateNaissanceBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT COUNT(p) FROM Patiente p WHERE p.updatedAt >= :startDate")
    Long countNewPatientesSince(@Param("startDate") LocalDate startDate);

    @Query("SELECT p FROM Patiente p LEFT JOIN FETCH p.dossierMedical WHERE p.id = :id")
    Optional<Patiente> findByIdWithDossier(@Param("id") Long id);

    @Query("SELECT p FROM Patiente p LEFT JOIN FETCH p.grossesses WHERE p.id = :id")
    Optional<Patiente> findByIdWithGrossesses(@Param("id") Long id);
}