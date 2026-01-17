package org.maternite.sante.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.AccouchementRequestDto;
import org.maternite.sante.dto.response.AccouchementResponseDto;
import org.maternite.sante.dto.response.ApiResponse;
import org.maternite.sante.service.AccouchementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accouchements")
@RequiredArgsConstructor
@Tag(name = "Gestion des Accouchements", description = "Endpoints pour le suivi des accouchements")
public class AccouchementController {

    private final AccouchementService accouchementService;

    @PostMapping
    @PreAuthorize("hasAnyRole('GYNECOLOGUE', 'SAGE_FEMME')")
    @Operation(summary = "Enregistrer un accouchement", description = "Permet de noter les détails d'un accouchement")
    public ResponseEntity<ApiResponse<AccouchementResponseDto>> createAccouchement(@Valid @RequestBody AccouchementRequestDto dto) {
        AccouchementResponseDto accouchement = accouchementService.createAccouchement(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Accouchement enregistré avec succès", accouchement));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('GYNECOLOGUE', 'SAGE_FEMME', 'PEDIATRE', 'PERSONNEL_ADMIN')")
    @Operation(summary = "Lister les accouchements", description = "Retourne la liste de tous les accouchements enregistrés")
    public ResponseEntity<ApiResponse<List<AccouchementResponseDto>>> getAllAccouchements() {
        List<AccouchementResponseDto> accouchements = accouchementService.getAllAccouchements();
        return ResponseEntity.ok(ApiResponse.success(accouchements));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('GYNECOLOGUE', 'SAGE_FEMME', 'PEDIATRE', 'PERSONNEL_ADMIN')")
    @Operation(summary = "Détails d'un accouchement", description = "Récupère les informations d'un accouchement par son ID")
    public ResponseEntity<ApiResponse<AccouchementResponseDto>> getAccouchementById(@PathVariable Long id) {
        AccouchementResponseDto accouchement = accouchementService.getAccouchementById(id);
        return ResponseEntity.ok(ApiResponse.success(accouchement));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('GYNECOLOGUE', 'SAGE_FEMME')")
    @Operation(summary = "Modifier un accouchement", description = "Permet de mettre à jour les informations d'un accouchement")
    public ResponseEntity<ApiResponse<AccouchementResponseDto>> updateAccouchement(@PathVariable Long id, @Valid @RequestBody AccouchementRequestDto dto) {
        AccouchementResponseDto accouchement = accouchementService.updateAccouchement(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Accouchement mis à jour avec succès", accouchement));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PERSONNEL_ADMIN')")
    @Operation(summary = "Supprimer un accouchement", description = "Supprime un enregistrement d'accouchement (Admin uniquement)")
    public ResponseEntity<ApiResponse<Void>> deleteAccouchement(@PathVariable Long id) {
        accouchementService.deleteAccouchement(id);
        return ResponseEntity.ok(ApiResponse.success("Accouchement supprimé avec succès"));
    }
}
