package org.maternite.sante.repository;

import org.maternite.sante.model.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Long> {

    Optional<DossierMedical> findByPatienteId(Long patienteId);

    Boolean existsByPatienteId(Long patienteId);

    @Query("SELECT dm FROM DossierMedical dm WHERE dm.patiente.id = :patienteId")
    Optional<DossierMedical> findDossierByPatiente(@Param("patienteId") Long patienteId);

    @Query("SELECT dm FROM DossierMedical dm LEFT JOIN FETCH dm.patiente WHERE dm.id = :id")
    Optional<DossierMedical> findByIdWithPatiente(@Param("id") Long id);
}
