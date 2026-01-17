package org.maternite.sante.config;

import lombok.RequiredArgsConstructor;
import org.maternite.sante.model.*;
import org.maternite.sante.model.enums.*;
import org.maternite.sante.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UtilisateurRepository utilisateurRepository;
    private final PatienteRepository patienteRepository;
    private final GrossesseRepository grossesseRepository;
    private final AccouchementRepository accouchementRepository;
    private final NouveauNeRepository nouveauNeRepository;
    private final ConsultationRepository consultationRepository;
    private final SalleAccouchementRepository salleAccouchementRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicamentRepository medicamentRepository;
    private final SoinRepository soinRepository;
    private final DossierMedicalRepository dossierMedicalRepository;
    private final DossierNeonatalRepository dossierNeonatalRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (utilisateurRepository.count() == 0) {
            initData();
        } else {
            System.out.println("La base de données contient déjà des données.");
        }
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    private void initData() {
        System.out.println("Initialisation des données de test...");

        // 1. Utilisateurs (Un pour chaque rôle)
        Utilisateur admin = utilisateurRepository.save(Utilisateur.builder()
                .nom("Diop").prenom("Amadou").email("admin@maternite.org").password(encoder().encode("passer123"))
                .role(RoleType.PERSONNEL_ADMIN).telephone("771234567").matricule("ADM001").estDisponible(true).build());

        Utilisateur gyneco = utilisateurRepository.save(Utilisateur.builder()
                .nom("Sow").prenom("Fatou").email("gyneco@maternite.org").password(encoder().encode("passer123"))
                .role(RoleType.GYNECOLOGUE).telephone("772345678").matricule("GYN001").estDisponible(true).build());

        Utilisateur sageFemme = utilisateurRepository.save(Utilisateur.builder()
                .nom("Ndiaye").prenom("Mariama").email("sagefemme@maternite.org").password(encoder().encode("passer123"))
                .role(RoleType.SAGE_FEMME).telephone("773456789").matricule("SF001").estDisponible(true).build());

        Utilisateur pediatre = utilisateurRepository.save(Utilisateur.builder()
                .nom("Fall").prenom("Moussa").email("pediatre@maternite.org").password(encoder().encode("passer123"))
                .role(RoleType.PEDIATRE).telephone("774567890").matricule("PED001").estDisponible(true).build());

        Utilisateur infirmier = utilisateurRepository.save(Utilisateur.builder()
                .nom("Gueye").prenom("Ibrahima").email("infirmier@maternite.org").password(encoder().encode("passer123"))
                .role(RoleType.INFIRMIER).telephone("775678901").matricule("INF001").estDisponible(true).build());

        // 2. Salles d'accouchement
        SalleAccouchement salle1 = salleAccouchementRepository.save(SalleAccouchement.builder()
                .numeroSalle(101).statut(StatutSalle.DISPONIBLE).estDisponible(true).build());
        SalleAccouchement salle2 = salleAccouchementRepository.save(SalleAccouchement.builder()
                .numeroSalle(102).statut(StatutSalle.DISPONIBLE).estDisponible(true).build());

        // 3. Patientes
        Patiente p1 = patienteRepository.save(Patiente.builder()
                .nom("Ba").prenom("Aissatou").dateNaissance(LocalDate.of(1995, 5, 15))
                .adresse("Dakar, Plateau").telephone("776543210").groupeSanguin(GroupeSanguin.O_POSITIF).build());

        Patiente p2 = patienteRepository.save(Patiente.builder()
                .nom("Kane").prenom("Aminata").dateNaissance(LocalDate.of(1998, 8, 20))
                .adresse("Dakar, Medina").telephone("777654321").groupeSanguin(GroupeSanguin.A_NEGATIF).build());

        Patiente p3 = patienteRepository.save(Patiente.builder()
                .nom("Sarr").prenom("Khady").dateNaissance(LocalDate.of(1992, 3, 10))
                .adresse("Dakar, Ouakam").telephone("778765432").groupeSanguin(GroupeSanguin.B_POSITIF).build());

        // 4. Dossiers Médicaux
        dossierMedicalRepository.save(DossierMedical.builder().patiente(p1).antecedentsMedicaux("Aucun").observations("Bon état général").build());
        dossierMedicalRepository.save(DossierMedical.builder().patiente(p2).antecedentsMedicaux("Asthme").observations("À surveiller").build());
        dossierMedicalRepository.save(DossierMedical.builder().patiente(p3).antecedentsMedicaux("Aucun").observations("RAS").build());

        // 5. Grossesses
        Grossesse g1 = grossesseRepository.save(Grossesse.builder()
                .patiente(p1).dateDebut(LocalDate.now().minusMonths(9)).datePrevue(LocalDate.now().plusDays(5))
                .etatGrossesse(EtatGrossesse.TERMINEE).nombreFoetus(1).build());

        Grossesse g2 = grossesseRepository.save(Grossesse.builder()
                .patiente(p2).dateDebut(LocalDate.now().minusMonths(4)).datePrevue(LocalDate.now().plusMonths(5))
                .etatGrossesse(EtatGrossesse.EN_COURS).nombreFoetus(1).build());

        Grossesse g3 = grossesseRepository.save(Grossesse.builder()
                .patiente(p3).dateDebut(LocalDate.now().minusMonths(9)).datePrevue(LocalDate.now().minusDays(2))
                .etatGrossesse(EtatGrossesse.TERMINEE).nombreFoetus(2).build());

        // 6. Accouchements
        Accouchement acc1 = accouchementRepository.save(Accouchement.builder()
                .grossesse(g1).heureDebut(LocalTime.of(8, 0)).heureFin(LocalTime.of(10, 30))
                .type(TypeAccouchement.VOIE_BASSE).salle(salle1).build());

        Accouchement acc3 = accouchementRepository.save(Accouchement.builder()
                .grossesse(g3).heureDebut(LocalTime.of(14, 0)).heureFin(LocalTime.of(16, 0))
                .type(TypeAccouchement.CESARIENNE).salle(salle2).build());

        // 7. Nouveau-nés
        NouveauNe nn1 = nouveauNeRepository.save(NouveauNe.builder()
                .accouchement(acc1).nom("Ba").prenom("Oumar").sexe(Sexe.MASCULIN)
                .poidsNaissance(3.2).tailleNaissance(50.0).dateNaissance(LocalDate.now()).build());

        NouveauNe nn2 = nouveauNeRepository.save(NouveauNe.builder()
                .accouchement(acc3).nom("Sarr").prenom("Fatou").sexe(Sexe.FEMININ)
                .poidsNaissance(2.8).tailleNaissance(48.0).dateNaissance(LocalDate.now()).build());

        NouveauNe nn3 = nouveauNeRepository.save(NouveauNe.builder()
                .accouchement(acc3).nom("Sarr").prenom("Moussa").sexe(Sexe.MASCULIN)
                .poidsNaissance(2.9).tailleNaissance(49.0).dateNaissance(LocalDate.now()).build());

        // 8. Dossiers Néonatals
        dossierNeonatalRepository.save(DossierNeonatal.builder().nouveauNe(nn1).dateCreation(LocalDateTime.now()).facteurRh("+").build());
        dossierNeonatalRepository.save(DossierNeonatal.builder().nouveauNe(nn2).dateCreation(LocalDateTime.now()).facteurRh("+").build());
        dossierNeonatalRepository.save(DossierNeonatal.builder().nouveauNe(nn3).dateCreation(LocalDateTime.now()).facteurRh("+").build());

        // 9. Consultations
        consultationRepository.save(Consultation.builder()
                .patiente(p1).personnel(gyneco).dateConsultation(LocalDate.now()).poids(75.0).temperature(37.0).tensionArterielle(12).observations("Suivi post-natal").build());
        consultationRepository.save(Consultation.builder()
                .patiente(p2).personnel(sageFemme).dateConsultation(LocalDate.now()).poids(70.0).temperature(36.8).tensionArterielle(11).observations("Suivi prénatal 4ème mois").build());
        consultationRepository.save(Consultation.builder()
                .nouveauNe(nn1).personnel(pediatre).dateConsultation(LocalDate.now()).poids(3.5).temperature(37.2).observations("Premier examen néonatal").build());

        // 10. Prescriptions
        Prescription pres1 = prescriptionRepository.save(Prescription.builder()
                .patiente(p1).personnel(gyneco).observations("Vitamines et repos").build());

        // 11. Médicaments
        medicamentRepository.save(Medicament.builder()
                .prescription(pres1).nom("Fer").dosage("1 comp/jour").type(TypeMedicament.COMPRIMES).dureeTraitement(30).quantite(30).build());

        // 12. Soins
        soinRepository.save(Soin.builder()
                .nouveauNe(nn1).typeSoin("Vaccination BCG").observations("Fait").build());

        System.out.println("Données de test initialisées avec succès.");
    }
}
