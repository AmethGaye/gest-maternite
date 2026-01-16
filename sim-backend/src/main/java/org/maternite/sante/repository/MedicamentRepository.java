package org.maternite.sante.repository;

import org.maternite.sante.model.Medicament;
import org.maternite.sante.model.enums.TypeMedicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Long> {

    List<Medicament> findByPrescriptionId(Long prescriptionId);

    List<Medicament> findByType(TypeMedicament type);

    List<Medicament> findByNomContainingIgnoreCase(String nom);

    @Query("SELECT m FROM Medicament m WHERE m.prescription.id = :prescriptionId")
    List<Medicament> findMedicamentsByPrescription(@Param("prescriptionId") Long prescriptionId);

    @Query("SELECT m FROM Medicament m WHERE m.prescription.patiente.id = :patienteId")
    List<Medicament> findMedicamentsByPatiente(@Param("patienteId") Long patienteId);

    @Query("SELECT m.nom, COUNT(m) FROM Medicament m GROUP BY m.nom ORDER BY COUNT(m) DESC")
    List<Object[]> findMedicamentsMostPrescribed();
}
