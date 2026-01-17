package org.maternite.sante.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.SoinRequestDto;
import org.maternite.sante.dto.response.ApiResponse;
import org.maternite.sante.dto.response.SoinResponseDto;
import org.maternite.sante.service.SoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soins")
@RequiredArgsConstructor
@Tag(name = "Gestion des Soins", description = "Endpoints pour le suivi des soins administrés aux nouveau-nés")
public class SoinController {

    private final SoinService soinService;

    @PostMapping
    @Operation(summary = "Enregistrer un soin", description = "Permet de noter un soin prodigué à un nouveau-né")
    public ResponseEntity<ApiResponse<SoinResponseDto>> createSoin(@Valid @RequestBody SoinRequestDto dto) {
        SoinResponseDto soin = soinService.createSoin(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Soin enregistré avec succès", soin));
    }

    @GetMapping
    @Operation(summary = "Lister les soins", description = "Retourne la liste de tous les soins enregistrés")
    public ResponseEntity<ApiResponse<List<SoinResponseDto>>> getAllSoins() {
        List<SoinResponseDto> soins = soinService.getAllSoins();
        return ResponseEntity.ok(ApiResponse.success(soins));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Détails d'un soin", description = "Récupère les détails d'un soin par son ID")
    public ResponseEntity<ApiResponse<SoinResponseDto>> getSoinById(@PathVariable Long id) {
        SoinResponseDto soin = soinService.getSoinById(id);
        return ResponseEntity.ok(ApiResponse.success(soin));
    }

    @GetMapping("/nouveau-ne/{nouveauNeId}")
    @Operation(summary = "Soins par nouveau-né", description = "Liste tous les soins administrés à un nouveau-né spécifique")
    public ResponseEntity<ApiResponse<List<SoinResponseDto>>> getSoinsByNouveauNe(@PathVariable Long nouveauNeId) {
        List<SoinResponseDto> soins = soinService.getSoinsByNouveauNe(nouveauNeId);
        return ResponseEntity.ok(ApiResponse.success(soins));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un soin", description = "Met à jour les informations d'un soin existant")
    public ResponseEntity<ApiResponse<SoinResponseDto>> updateSoin(
            @PathVariable Long id,
            @Valid @RequestBody SoinRequestDto dto) {
        SoinResponseDto soin = soinService.updateSoin(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Soin mis à jour avec succès", soin));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PERSONNEL_ADMIN')")
    @Operation(summary = "Supprimer un soin", description = "Supprime l'enregistrement d'un soin (Admin uniquement)")
    public ResponseEntity<ApiResponse<Void>> deleteSoin(@PathVariable Long id) {
        soinService.deleteSoin(id);
        return ResponseEntity.ok(ApiResponse.success("Soin supprimé avec succès"));
    }
}
