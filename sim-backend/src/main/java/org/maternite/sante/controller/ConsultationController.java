package org.maternite.sante.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.ConsultationRequestDto;
import org.maternite.sante.dto.response.ApiResponse;
import org.maternite.sante.dto.response.ConsultationResponseDto;
import org.maternite.sante.service.ConsultationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/consultations")
@RequiredArgsConstructor
@Tag(name = "Gestion des Consultations", description = "Endpoints pour le suivi des consultations médicales")
public class ConsultationController {

    private final ConsultationService consultationService;

    @PostMapping
    @Operation(summary = "Créer une consultation", description = "Enregistre une nouvelle consultation pour une patiente ou un nouveau-né")
    public ResponseEntity<ApiResponse<ConsultationResponseDto>> createConsultation(
            @Valid @RequestBody ConsultationRequestDto dto) {
        ConsultationResponseDto consultation = consultationService.createConsultation(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Consultation créée avec succès", consultation));
    }

    @GetMapping
    @Operation(summary = "Lister les consultations", description = "Retourne la liste de toutes les consultations")
    public ResponseEntity<ApiResponse<List<ConsultationResponseDto>>> getAllConsultations() {
        List<ConsultationResponseDto> consultations = consultationService.getAllConsultations();
        return ResponseEntity.ok(ApiResponse.success(consultations));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Détails d'une consultation", description = "Récupère une consultation par son ID")
    public ResponseEntity<ApiResponse<ConsultationResponseDto>> getConsultationById(@PathVariable Long id) {
        ConsultationResponseDto consultation = consultationService.getConsultationById(id);
        return ResponseEntity.ok(ApiResponse.success(consultation));
    }

    @GetMapping("/patiente/{patienteId}")
    @Operation(summary = "Consultations par patiente", description = "Liste toutes les consultations d'une patiente")
    public ResponseEntity<ApiResponse<List<ConsultationResponseDto>>> getConsultationsByPatiente(@PathVariable Long patienteId) {
        List<ConsultationResponseDto> consultations = consultationService.getConsultationsByPatiente(patienteId);
        return ResponseEntity.ok(ApiResponse.success(consultations));
    }

    @GetMapping("/nouveau-ne/{nouveauNeId}")
    @Operation(summary = "Consultations par nouveau-né", description = "Liste toutes les consultations d'un nouveau-né")
    public ResponseEntity<ApiResponse<List<ConsultationResponseDto>>> getConsultationsByNouveauNe(@PathVariable Long nouveauNeId) {
        List<ConsultationResponseDto> consultations = consultationService.getConsultationsByNouveauNe(nouveauNeId);
        return ResponseEntity.ok(ApiResponse.success(consultations));
    }

    @GetMapping("/personnel/{personnelId}")
    @Operation(summary = "Consultations par personnel", description = "Liste toutes les consultations effectuées par un membre du personnel")
    public ResponseEntity<ApiResponse<List<ConsultationResponseDto>>> getConsultationsByPersonnel(@PathVariable Long personnelId) {
        List<ConsultationResponseDto> consultations = consultationService.getConsultationsByPersonnel(personnelId);
        return ResponseEntity.ok(ApiResponse.success(consultations));
    }

    @GetMapping("/date")
    @Operation(summary = "Consultations par date", description = "Liste les consultations pour une date précise")
    public ResponseEntity<ApiResponse<List<ConsultationResponseDto>>> getConsultationsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<ConsultationResponseDto> consultations = consultationService.getConsultationsByDate(date);
        return ResponseEntity.ok(ApiResponse.success(consultations));
    }

    @GetMapping("/between-dates")
    @Operation(summary = "Consultations entre deux dates", description = "Liste les consultations effectuées dans un intervalle de temps")
    public ResponseEntity<ApiResponse<List<ConsultationResponseDto>>> getConsultationsBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ConsultationResponseDto> consultations = consultationService.getConsultationsBetweenDates(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success(consultations));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une consultation", description = "Met à jour les informations d'une consultation existante")
    public ResponseEntity<ApiResponse<ConsultationResponseDto>> updateConsultation(
            @PathVariable Long id,
            @Valid @RequestBody ConsultationRequestDto dto) {
        ConsultationResponseDto consultation = consultationService.updateConsultation(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Consultation mise à jour avec succès", consultation));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PERSONNEL_ADMIN')")
    @Operation(summary = "Supprimer une consultation", description = "Supprime une consultation (Admin uniquement)")
    public ResponseEntity<ApiResponse<Void>> deleteConsultation(@PathVariable Long id) {
        consultationService.deleteConsultation(id);
        return ResponseEntity.ok(ApiResponse.success("Consultation supprimée avec succès"));
    }

    @GetMapping("/count/personnel/{personnelId}/date")
    @Operation(summary = "Nombre de consultations par jour", description = "Compte le nombre de consultations effectuées par un personnel à une date donnée")
    public ResponseEntity<ApiResponse<Long>> countConsultationsByPersonnelAndDate(
            @PathVariable Long personnelId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Long count = consultationService.countConsultationsByPersonnelAndDate(personnelId, date);
        return ResponseEntity.ok(ApiResponse.success(count));
    }
}
