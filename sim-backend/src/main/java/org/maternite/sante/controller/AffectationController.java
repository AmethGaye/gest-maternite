package org.maternite.sante.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.AffectationRequestDto;
import org.maternite.sante.dto.response.AffectationResponseDto;
import org.maternite.sante.dto.response.ApiResponse;
import org.maternite.sante.service.AffectationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/affectations")
@RequiredArgsConstructor
@Tag(name = "Gestion des Affectations", description = "Endpoints pour gérer les affectations du personnel")
public class AffectationController {

    private final AffectationService affectationService;

    @PostMapping
    @Operation(summary = "Créer une affectation", description = "Assigne un membre du personnel à un planning et une salle")
    public ResponseEntity<ApiResponse<AffectationResponseDto>> createAffectation(@Valid @RequestBody AffectationRequestDto dto) {
        AffectationResponseDto affectation = affectationService.createAffectation(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Affectation créée avec succès", affectation));
    }

    @GetMapping
    @Operation(summary = "Lister les affectations", description = "Retourne la liste de toutes les affectations")
    public ResponseEntity<ApiResponse<List<AffectationResponseDto>>> getAllAffectations() {
        List<AffectationResponseDto> affectations = affectationService.getAllAffectations();
        return ResponseEntity.ok(ApiResponse.success(affectations));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une affectation", description = "Retourne les détails d'une affectation par son ID")
    public ResponseEntity<ApiResponse<AffectationResponseDto>> getAffectationById(@PathVariable Long id) {
        AffectationResponseDto affectation = affectationService.getAffectationById(id);
        return ResponseEntity.ok(ApiResponse.success(affectation));
    }

    @GetMapping("/personnel/{personnelId}")
    @Operation(summary = "Affectations par personnel", description = "Liste toutes les affectations pour un membre du personnel spécifique")
    public ResponseEntity<ApiResponse<List<AffectationResponseDto>>> getAffectationsByPersonnel(@PathVariable Long personnelId) {
        List<AffectationResponseDto> affectations = affectationService.getAffectationsByPersonnel(personnelId);
        return ResponseEntity.ok(ApiResponse.success(affectations));
    }

    @GetMapping("/planning/{planningId}")
    @Operation(summary = "Affectations par planning", description = "Liste toutes les affectations liées à un planning spécifique")
    public ResponseEntity<ApiResponse<List<AffectationResponseDto>>> getAffectationsByPlanning(@PathVariable Long planningId) {
        List<AffectationResponseDto> affectations = affectationService.getAffectationsByPlanning(planningId);
        return ResponseEntity.ok(ApiResponse.success(affectations));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une affectation", description = "Met à jour les détails d'une affectation existante")
    public ResponseEntity<ApiResponse<AffectationResponseDto>> updateAffectation(
            @PathVariable Long id,
            @Valid @RequestBody AffectationRequestDto dto) {
        AffectationResponseDto affectation = affectationService.updateAffectation(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Affectation mise à jour avec succès", affectation));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PERSONNEL_ADMIN')")
    @Operation(summary = "Supprimer une affectation", description = "Supprime une affectation (Admin uniquement)")
    public ResponseEntity<ApiResponse<Void>> deleteAffectation(@PathVariable Long id) {
        affectationService.deleteAffectation(id);
        return ResponseEntity.ok(ApiResponse.success("Affectation supprimée avec succès"));
    }
}
