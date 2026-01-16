package org.maternite.sante.repository;

import org.maternite.sante.model.SalleAccouchement;
import org.maternite.sante.model.enums.StatutSalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalleAccouchementRepository extends JpaRepository<SalleAccouchement, Long> {

    Optional<SalleAccouchement> findByNumeroSalle(Integer numeroSalle);

    List<SalleAccouchement> findByStatut(StatutSalle statut);

    List<SalleAccouchement> findByEstDisponible(Boolean estDisponible);

    @Query("SELECT s FROM SalleAccouchement s WHERE s.statut = 'DISPONIBLE' AND s.estDisponible = true")
    List<SalleAccouchement> findSallesDisponibles();

    @Query("SELECT s FROM SalleAccouchement s WHERE s.statut = 'OCCUPEE'")
    List<SalleAccouchement> findSallesOccupees();

    @Query("SELECT COUNT(s) FROM SalleAccouchement s WHERE s.statut = :statut")
    Long countByStatut(@Param("statut") StatutSalle statut);

    Boolean existsByNumeroSalle(Integer numeroSalle);
}
