package org.maternite.sante;

import org.maternite.sante.model.Utilisateur;
import org.maternite.sante.model.enums.RoleType;
import org.maternite.sante.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class MaterniteApplication{

    public static void main(String[] args) {
        SpringApplication.run(MaterniteApplication.class, args);
    }


}
