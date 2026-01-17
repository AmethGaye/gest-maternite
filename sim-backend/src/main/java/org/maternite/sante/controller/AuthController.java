package org.maternite.sante.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.LoginRequest;
import org.maternite.sante.dto.request.RegisterRequest;
import org.maternite.sante.dto.response.ApiResponse;
import org.maternite.sante.dto.response.AuthResponse;
import org.maternite.sante.dto.response.UtilisateurResponseDto;
import org.maternite.sante.security.JwtTokenProvider;
import org.maternite.sante.security.UserPrincipal;
import org.maternite.sante.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentification", description = "Endpoints pour l'authentification et l'inscription")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurService utilisateurService;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    @Operation(summary = "Connexion utilisateur", description = "Permet à un utilisateur de se connecter et d'obtenir un jeton JWT")
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
    @Operation(summary = "Inscription utilisateur", description = "Permet de créer un nouveau compte utilisateur")
    public ResponseEntity<ApiResponse<UtilisateurResponseDto>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        UtilisateurResponseDto user = utilisateurService.createUser(registerRequest);
        return ResponseEntity.ok(ApiResponse.success("Utilisateur créé avec succès", user));
    }

    @GetMapping("/profile")
    @Operation(summary = "Profil actuel", description = "Retourne les informations de l'utilisateur actuellement connecté")
    public ResponseEntity<UserPrincipal> getCurrentUser(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(userPrincipal);
    }

    @PostMapping("/logout")
    @Operation(summary = "Déconnexion", description = "Permet de se déconnecter de l'application")
    public ResponseEntity<ApiResponse<Void>> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(ApiResponse.success("Déconnexion réussie"));
    }
}
