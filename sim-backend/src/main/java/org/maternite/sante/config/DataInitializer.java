package org.maternite.sante.config;

import org.maternite.sante.model.Utilisateur;
import org.maternite.sante.model.enums.RoleType;
import org.maternite.sante.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final UtilisateurRepository utilisateurRepository;

    public DataInitializer(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (utilisateurRepository.count() == 0) {
            Utilisateur admin = Utilisateur.builder()
                    .nom("Admin")
                    .prenom("System")
                    .email("admin@maternite.org")
                    .password("admin123") // Dans un projet réel, utilisez un PasswordEncoder
                    .role(RoleType.PERSONNEL_ADMIN)
                    .telephone("770000000")
                    .matricule("ADM001")
                    .estDisponible(true)
                    .build();

            utilisateurRepository.save(admin);
            System.out.println("Utilisateur par défaut créé : " + admin.getEmail());
        } else {
            System.out.println("La base de données contient déjà des utilisateurs.");
        }
    }
}
