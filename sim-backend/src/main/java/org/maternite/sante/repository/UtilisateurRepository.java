package org.maternite.sante.repository;

import org.maternite.sante.model.Utilisateur;
import org.maternite.sante.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByEmail(String email);

    Optional<Utilisateur> findByMatricule(String matricule);

    Boolean existsByEmail(String email);

    Boolean existsByMatricule(String matricule);

    List<Utilisateur> findByRole(RoleType role);

    List<Utilisateur> findByEstDisponible(Boolean estDisponible);

    @Query("SELECT u FROM Utilisateur u WHERE u.role = :role AND u.estDisponible = true")
    List<Utilisateur> findDisponiblesByRole(@Param("role") RoleType role);

    @Query("SELECT u FROM Utilisateur u WHERE LOWER(u.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(u.prenom) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Utilisateur> searchByNomOrPrenom(@Param("keyword") String keyword);
}
