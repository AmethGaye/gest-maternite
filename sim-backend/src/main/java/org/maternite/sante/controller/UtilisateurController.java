package org.maternite.sante.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.RegisterRequest;
import org.maternite.sante.dto.request.UtilisateurUpdateRequest;
import org.maternite.sante.dto.response.ApiResponse;
import org.maternite.sante.dto.response.UtilisateurResponseDto;
import org.maternite.sante.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/utilisateurs")
@RequiredArgsConstructor
@Tag(name = "Gestion des Utilisateurs", description = "Endpoints pour l'administration des utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping
    @PreAuthorize("hasRole('PERSONNEL_ADMIN')")
    @Operation(summary = "Créer un utilisateur", description = "Permet à l'administrateur de créer un nouveau compte personnel")
    public ResponseEntity<ApiResponse<UtilisateurResponseDto>> createUser(@Valid @RequestBody RegisterRequest request) {
        UtilisateurResponseDto user = utilisateurService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Utilisateur créé avec succès", user));
    }

    @GetMapping
    @PreAuthorize("hasRole('PERSONNEL_ADMIN')")
    @Operation(summary = "Lister tous les utilisateurs", description = "Retourne la liste de tout le personnel (Admin uniquement)")
    public ResponseEntity<ApiResponse<List<UtilisateurResponseDto>>> getAllUsers() {
        List<UtilisateurResponseDto> users = utilisateurService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success(users));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('PERSONNEL_ADMIN')")
    @Operation(summary = "Récupérer un utilisateur par ID", description = "Retourne les détails d'un membre du personnel")
    public ResponseEntity<ApiResponse<UtilisateurResponseDto>> getUserById(@PathVariable Long id) {
        UtilisateurResponseDto user = utilisateurService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PERSONNEL_ADMIN')")
    @Operation(summary = "Mettre à jour un utilisateur", description = "Permet de modifier les informations d'un utilisateur")
    public ResponseEntity<ApiResponse<UtilisateurResponseDto>> updateUser(
            @PathVariable Long id, 
            @Valid @RequestBody UtilisateurUpdateRequest request) {
        UtilisateurResponseDto user = utilisateurService.updateUser(id, request);
        return ResponseEntity.ok(ApiResponse.success("Utilisateur mis à jour avec succès", user));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PERSONNEL_ADMIN')")
    @Operation(summary = "Supprimer un utilisateur", description = "Permet de supprimer un compte utilisateur")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        utilisateurService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("Utilisateur supprimé avec succès"));
    }

    @PatchMapping("/{id}/toggle-disponibilite")
    @PreAuthorize("hasAnyRole('PERSONNEL_ADMIN', 'GYNECOLOGUE', 'SAGE_FEMME', 'PEDIATRE', 'INFIRMIER')")
    @Operation(summary = "Basculer la disponibilité", description = "Permet de changer le statut de disponibilité d'un personnel")
    public ResponseEntity<ApiResponse<UtilisateurResponseDto>> toggleDisponibilite(@PathVariable Long id) {
        UtilisateurResponseDto user = utilisateurService.toggleDisponibilite(id);
        return ResponseEntity.ok(ApiResponse.success("Statut de disponibilité mis à jour", user));
    }
}
