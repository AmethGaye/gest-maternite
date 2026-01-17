package org.maternite.sante.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.DossierMedicalRequestDto;
import org.maternite.sante.dto.request.PatienteRequestDto;
import org.maternite.sante.dto.response.DossierMedicalResponseDto;
import org.maternite.sante.dto.response.PatienteResponseDto;
import org.maternite.sante.service.DossierMedicalService;
import org.maternite.sante.service.PatienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/patientes")
@RequiredArgsConstructor
@Tag(name = "Gestion des Patientes", description = "Endpoints pour la gestion des patientes")
public class PatienteController {

    private final PatienteService patienteService;
    private final DossierMedicalService dossierMedicalService;

    @PostMapping
    @PreAuthorize("hasAnyRole('PERSONNEL_ADMIN', 'SAGE_FEMME', 'GYNECOLOGUE')")
    @Operation(summary = "Créer une nouvelle patiente", description = "Permet d'enregistrer une nouvelle patiente dans le système")
    public ResponseEntity<org.maternite.sante.dto.response.ApiResponse<PatienteResponseDto>> createPatiente(
            @Valid @RequestBody PatienteRequestDto dto) {

        PatienteResponseDto patiente = patienteService.createPatiente(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(org.maternite.sante.dto.response.ApiResponse.success("Patiente créée avec succès", patiente));
    }

    @GetMapping("/{id}/dossier-medical")
    @PreAuthorize("hasAnyRole('PERSONNEL_ADMIN', 'SAGE_FEMME', 'GYNECOLOGUE', 'INFIRMIER')")
    @Operation(summary = "Récupérer le dossier médical d'une patiente", description = "Retourne le dossier médical associé à la patiente")
    public ResponseEntity<org.maternite.sante.dto.response.ApiResponse<DossierMedicalResponseDto>> getDossierMedical(@PathVariable Long id) {
        DossierMedicalResponseDto dossier = dossierMedicalService.getDossierByPatiente(id);
        return ResponseEntity.ok(org.maternite.sante.dto.response.ApiResponse.success(dossier));
    }

    @PutMapping("/{id}/dossier-medical")
    @PreAuthorize("hasAnyRole('PERSONNEL_ADMIN', 'SAGE_FEMME', 'GYNECOLOGUE')")
    @Operation(summary = "Mettre à jour le dossier médical d'une patiente", description = "Permet de modifier le dossier médical d'une patiente")
    public ResponseEntity<org.maternite.sante.dto.response.ApiResponse<DossierMedicalResponseDto>> updateDossierMedical(
            @PathVariable Long id,
            @Valid @RequestBody DossierMedicalRequestDto dto) {
        DossierMedicalResponseDto dossier = dossierMedicalService.updateDossierByPatiente(id, dto);
        return ResponseEntity.ok(org.maternite.sante.dto.response.ApiResponse.success("Dossier médical mis à jour avec succès", dossier));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PERSONNEL_ADMIN', 'SAGE_FEMME', 'GYNECOLOGUE', 'INFIRMIER')")
    @Operation(summary = "Lister toutes les patientes", description = "Retourne la liste complète des patientes")
    public ResponseEntity<org.maternite.sante.dto.response.ApiResponse<List<PatienteResponseDto>>> getAllPatientes() {
        List<PatienteResponseDto> patientes = patienteService.getAllPatientes();
        return ResponseEntity.ok(org.maternite.sante.dto.response.ApiResponse.success(patientes));
    }

    @GetMapping("/page")
    @PreAuthorize("hasAnyRole('PERSONNEL_ADMIN', 'SAGE_FEMME', 'GYNECOLOGUE', 'INFIRMIER')")
    @Operation(summary = "Lister les patientes avec pagination", description = "Retourne une page de patientes")
    public ResponseEntity<Page<PatienteResponseDto>> getAllPatientesPage(Pageable pageable) {
        return ResponseEntity.ok(patienteService.getAllPatientes(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PERSONNEL_ADMIN', 'SAGE_FEMME', 'GYNECOLOGUE', 'INFIRMIER')")
    @Operation(summary = "Récupérer une patiente par son ID", description = "Retourne les détails d'une patiente spécifique")
    public ResponseEntity<org.maternite.sante.dto.response.ApiResponse<PatienteResponseDto>> getPatienteById(@PathVariable Long id) {
        PatienteResponseDto patiente = patienteService.getPatienteById(id);
        return ResponseEntity.ok(org.maternite.sante.dto.response.ApiResponse.success(patiente));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('PERSONNEL_ADMIN', 'SAGE_FEMME', 'GYNECOLOGUE')")
    @Operation(summary = "Mettre à jour une patiente", description = "Permet de modifier les informations d'une patiente existante")
    public ResponseEntity<org.maternite.sante.dto.response.ApiResponse<PatienteResponseDto>> updatePatiente(
            @PathVariable Long id,
            @Valid @RequestBody PatienteRequestDto dto) {

        PatienteResponseDto patiente = patienteService.updatePatiente(id, dto);
        return ResponseEntity.ok(org.maternite.sante.dto.response.ApiResponse.success("Patiente mise à jour avec succès", patiente));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PERSONNEL_ADMIN')")
    @Operation(summary = "Supprimer une patiente", description = "Permet de supprimer une patiente du système (Admin uniquement)")
    public ResponseEntity<org.maternite.sante.dto.response.ApiResponse<Void>> deletePatiente(@PathVariable Long id) {
        patienteService.deletePatiente(id);
        return ResponseEntity.ok(org.maternite.sante.dto.response.ApiResponse.success("Patiente supprimée avec succès"));
    }

}
