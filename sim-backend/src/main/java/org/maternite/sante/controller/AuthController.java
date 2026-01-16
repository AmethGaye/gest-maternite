package org.maternite.sante.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.LoginRequest;
import org.maternite.sante.dto.request.RegisterRequest;
import org.maternite.sante.dto.response.AuthResponse;
import org.maternite.sante.exceptions.BadRequestException;
import org.maternite.sante.model.Utilisateur;
import org.maternite.sante.repository.UtilisateurRepository;
import org.maternite.sante.security.JwtTokenProvider;
import org.maternite.sante.security.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = tokenProvider.generateToken(authentication);
        String refreshToken = tokenProvider.generateRefreshToken(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        AuthResponse response = AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(userPrincipal.getId())
                .email(userPrincipal.getEmail())
                .nom(userPrincipal.getNom())
                .prenom(userPrincipal.getPrenom())
                .role(userPrincipal.getRole())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        // Vérifier si l'email existe déjà
        if (utilisateurRepository.existsByEmail(registerRequest.getEmail())) {
            throw new BadRequestException("Cet email est déjà utilisé");
        }

        // Vérifier si le matricule existe déjà
        if (registerRequest.getMatricule() != null &&
                utilisateurRepository.existsByMatricule(registerRequest.getMatricule())) {
            throw new BadRequestException("Ce matricule est déjà utilisé");
        }

        // Créer le nouvel utilisateur
        Utilisateur utilisateur = Utilisateur.builder()
                .nom(registerRequest.getNom())
                .prenom(registerRequest.getPrenom())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .telephone(registerRequest.getTelephone())
                .matricule(registerRequest.getMatricule())
                .estDisponible(true)
                .build();

        Utilisateur savedUser = utilisateurRepository.save(utilisateur);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Utilisateur créé avec succès avec l'ID : " + savedUser.getId());
    }

    @GetMapping("/profile")
    public ResponseEntity<UserPrincipal> getCurrentUser(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(userPrincipal);
    }
}
