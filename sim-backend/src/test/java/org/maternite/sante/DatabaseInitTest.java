package org.maternite.sante;

import org.junit.jupiter.api.Test;
import org.maternite.sante.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("dev")
class DatabaseInitTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private org.springframework.boot.CommandLineRunner dataInitializer;

    @Test
    void testDataLoaded() {
        long count = utilisateurRepository.count();
        System.out.println("[DEBUG_LOG] Nombre d'utilisateurs en base : " + count);
        assertThat(count).isGreaterThan(0);
    }
}
