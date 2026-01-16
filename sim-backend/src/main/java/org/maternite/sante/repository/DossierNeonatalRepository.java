package org.maternite.sante.repository;

import org.maternite.sante.model.DossierNeonatal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DossierNeonatalRepository extends JpaRepository<DossierNeonatal, Long> {

    Optional<DossierNeonatal> findByNouveauNeId(Long nouveauNeId);

    Boolean existsByNouveauNeId(Long nouveauNeId);

    @Query("SELECT dn FROM DossierNeonatal dn WHERE dn.nouveauNe.id = :nouveauNeId")
    Optional<DossierNeonatal> findDossierByNouveauNe(@Param("nouveauNeId") Long nouveauNeId);

    @Query("SELECT dn FROM DossierNeonatal dn LEFT JOIN FETCH dn.nouveauNe WHERE dn.id = :id")
    Optional<DossierNeonatal> findByIdWithNouveauNe(@Param("id") Long id);
}
