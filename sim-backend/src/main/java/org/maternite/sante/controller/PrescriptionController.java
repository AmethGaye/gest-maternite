package org.maternite.sante.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.PrescriptionRequestDto;
import org.maternite.sante.dto.response.ApiResponse;
import org.maternite.sante.dto.response.PrescriptionResponseDto;
import org.maternite.sante.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescriptions")
@RequiredArgsConstructor
@Tag(name = "Gestion des Prescriptions", description = "Endpoints pour la gestion des prescriptions médicales")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('PERSONNEL_ADMIN', 'GYNECOLOGUE', 'PEDIATRE')")
    @Operation(summary = "Créer une prescription", description = "Permet à un médecin d'ajouter une nouvelle prescription")
    public ResponseEntity<ApiResponse<PrescriptionResponseDto>> createPrescription(
            @Valid @RequestBody PrescriptionRequestDto dto) {
        PrescriptionResponseDto prescription = prescriptionService.createPrescription(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Prescription créée avec succès", prescription));
    }

    @GetMapping
    @Operation(summary = "Lister les prescriptions", description = "Retourne la liste de toutes les prescriptions")
    public ResponseEntity<ApiResponse<List<PrescriptionResponseDto>>> getAllPrescriptions() {
        List<PrescriptionResponseDto> prescriptions = prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(ApiResponse.success(prescriptions));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Détails d'une prescription", description = "Récupère une prescription par son ID")
    public ResponseEntity<ApiResponse<PrescriptionResponseDto>> getPrescriptionById(@PathVariable Long id) {
        PrescriptionResponseDto prescription = prescriptionService.getPrescriptionById(id);
        return ResponseEntity.ok(ApiResponse.success(prescription));
    }

    @GetMapping("/patiente/{patienteId}")
    @Operation(summary = "Prescriptions par patiente", description = "Liste toutes les prescriptions d'une patiente")
    public ResponseEntity<ApiResponse<List<PrescriptionResponseDto>>> getPrescriptionsByPatiente(@PathVariable Long patienteId) {
        List<PrescriptionResponseDto> prescriptions = prescriptionService.getPrescriptionsByPatiente(patienteId);
        return ResponseEntity.ok(ApiResponse.success(prescriptions));
    }

    @GetMapping("/personnel/{personnelId}")
    @Operation(summary = "Prescriptions par personnel", description = "Liste toutes les prescriptions rédigées par un membre du personnel")
    public ResponseEntity<ApiResponse<List<PrescriptionResponseDto>>> getPrescriptionsByPersonnel(@PathVariable Long personnelId) {
        List<PrescriptionResponseDto> prescriptions = prescriptionService.getPrescriptionsByPersonnel(personnelId);
        return ResponseEntity.ok(ApiResponse.success(prescriptions));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PERSONNEL_ADMIN')")
    @Operation(summary = "Supprimer une prescription", description = "Supprime une prescription (Admin uniquement)")
    public ResponseEntity<ApiResponse<Void>> deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.ok(ApiResponse.success("Prescription supprimée avec succès"));
    }
}
