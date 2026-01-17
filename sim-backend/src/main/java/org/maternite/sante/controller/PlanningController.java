package org.maternite.sante.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.PlanningRequestDto;
import org.maternite.sante.dto.response.ApiResponse;
import org.maternite.sante.dto.response.PlanningResponseDto;
import org.maternite.sante.service.PlanningService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plannings")
@RequiredArgsConstructor
@Tag(name = "Gestion des Plannings", description = "Endpoints pour la gestion des plannings de garde")
public class PlanningController {

    private final PlanningService planningService;

    @PostMapping
    @Operation(summary = "Créer un planning", description = "Définit une nouvelle période de planning")
    public ResponseEntity<ApiResponse<PlanningResponseDto>> createPlanning(@Valid @RequestBody PlanningRequestDto dto) {
        PlanningResponseDto planning = planningService.createPlanning(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Planning créé avec succès", planning));
    }

    @GetMapping
    @Operation(summary = "Lister les plannings", description = "Retourne tous les plannings enregistrés")
    public ResponseEntity<ApiResponse<List<PlanningResponseDto>>> getAllPlannings() {
        List<PlanningResponseDto> plannings = planningService.getAllPlannings();
        return ResponseEntity.ok(ApiResponse.success(plannings));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un planning", description = "Retourne les détails d'un planning par son ID")
    public ResponseEntity<ApiResponse<PlanningResponseDto>> getPlanningById(@PathVariable Long id) {
        PlanningResponseDto planning = planningService.getPlanningById(id);
        return ResponseEntity.ok(ApiResponse.success(planning));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un planning", description = "Met à jour les informations d'un planning")
    public ResponseEntity<ApiResponse<PlanningResponseDto>> updatePlanning(
            @PathVariable Long id,
            @Valid @RequestBody PlanningRequestDto dto) {
        PlanningResponseDto planning = planningService.updatePlanning(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Planning mis à jour avec succès", planning));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PERSONNEL_ADMIN')")
    @Operation(summary = "Supprimer un planning", description = "Supprime un planning (Admin uniquement)")
    public ResponseEntity<ApiResponse<Void>> deletePlanning(@PathVariable Long id) {
        planningService.deletePlanning(id);
        return ResponseEntity.ok(ApiResponse.success("Planning supprimé avec succès"));
    }
}
