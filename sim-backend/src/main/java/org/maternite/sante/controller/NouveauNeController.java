package org.maternite.sante.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.DossierNeonatalRequestDto;
import org.maternite.sante.dto.request.NouveauNeRequestDto;
import org.maternite.sante.dto.response.ApiResponse;
import org.maternite.sante.dto.response.DossierNeonatalResponseDto;
import org.maternite.sante.dto.response.NouveauNeResponseDto;
import org.maternite.sante.service.DossierNeonatalService;
import org.maternite.sante.service.NouveauNeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nouveau-nes")
@RequiredArgsConstructor
@Tag(name = "Gestion des Nouveau-nés", description = "Endpoints pour le suivi des nouveau-nés")
public class NouveauNeController {

    private final NouveauNeService nouveauNeService;
    private final DossierNeonatalService dossierNeonatalService;

    @PostMapping
    @PreAuthorize("hasAnyRole('GYNECOLOGUE', 'SAGE_FEMME')")
    @Operation(summary = "Enregistrer un nouveau-né", description = "Permet d'ajouter les informations d'un nouveau-né après un accouchement")
    public ResponseEntity<ApiResponse<NouveauNeResponseDto>> createNouveauNe(@Valid @RequestBody NouveauNeRequestDto dto) {
        NouveauNeResponseDto nouveauNe = nouveauNeService.createNouveauNe(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Nouveau-né enregistré avec succès", nouveauNe));
    }

    @GetMapping("/{id}/dossier-neonatal")
    @PreAuthorize("hasAnyRole('GYNECOLOGUE', 'SAGE_FEMME', 'PEDIATRE', 'PERSONNEL_ADMIN')")
    @Operation(summary = "Récupérer le dossier néonatal d'un nouveau-né", description = "Retourne le dossier néonatal associé au nouveau-né")
    public ResponseEntity<ApiResponse<DossierNeonatalResponseDto>> getDossierNeonatal(@PathVariable Long id) {
        DossierNeonatalResponseDto dossier = dossierNeonatalService.getDossierByNouveauNe(id);
        return ResponseEntity.ok(ApiResponse.success(dossier));
    }

    @PutMapping("/{id}/dossier-neonatal")
    @PreAuthorize("hasAnyRole('GYNECOLOGUE', 'SAGE_FEMME', 'PEDIATRE')")
    @Operation(summary = "Mettre à jour le dossier néonatal d'un nouveau-né", description = "Permet de modifier le dossier néonatal d'un nouveau-né")
    public ResponseEntity<ApiResponse<DossierNeonatalResponseDto>> updateDossierNeonatal(
            @PathVariable Long id,
            @Valid @RequestBody DossierNeonatalRequestDto dto) {
        DossierNeonatalResponseDto dossier = dossierNeonatalService.updateDossierByNouveauNe(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Dossier néonatal mis à jour avec succès", dossier));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('GYNECOLOGUE', 'SAGE_FEMME', 'PEDIATRE', 'PERSONNEL_ADMIN')")
    @Operation(summary = "Lister les nouveau-nés", description = "Retourne la liste de tous les nouveau-nés")
    public ResponseEntity<ApiResponse<List<NouveauNeResponseDto>>> getAllNouveauNes() {
        List<NouveauNeResponseDto> nouveauNes = nouveauNeService.getAllNouveauNes();
        return ResponseEntity.ok(ApiResponse.success(nouveauNes));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('GYNECOLOGUE', 'SAGE_FEMME', 'PEDIATRE', 'PERSONNEL_ADMIN')")
    @Operation(summary = "Récupérer un nouveau-né", description = "Retourne les détails d'un nouveau-né par son ID")
    public ResponseEntity<ApiResponse<NouveauNeResponseDto>> getNouveauNeById(@PathVariable Long id) {
        NouveauNeResponseDto nouveauNe = nouveauNeService.getNouveauNeById(id);
        return ResponseEntity.ok(ApiResponse.success(nouveauNe));
    }

    @GetMapping("/accouchement/{accouchementId}")
    @PreAuthorize("hasAnyRole('GYNECOLOGUE', 'SAGE_FEMME', 'PEDIATRE', 'PERSONNEL_ADMIN')")
    @Operation(summary = "Nouveau-nés par accouchement", description = "Liste les nouveau-nés issus d'un accouchement spécifique")
    public ResponseEntity<ApiResponse<List<NouveauNeResponseDto>>> getNouveauNesByAccouchementId(@PathVariable Long accouchementId) {
        List<NouveauNeResponseDto> nouveauNes = nouveauNeService.getNouveauNesByAccouchementId(accouchementId);
        return ResponseEntity.ok(ApiResponse.success(nouveauNes));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('GYNECOLOGUE', 'SAGE_FEMME', 'PEDIATRE')")
    @Operation(summary = "Modifier un nouveau-né", description = "Met à jour les informations d'un nouveau-né")
    public ResponseEntity<ApiResponse<NouveauNeResponseDto>> updateNouveauNe(@PathVariable Long id, @Valid @RequestBody NouveauNeRequestDto dto) {
        NouveauNeResponseDto nouveauNe = nouveauNeService.updateNouveauNe(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Nouveau-né mis à jour avec succès", nouveauNe));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PERSONNEL_ADMIN')")
    @Operation(summary = "Supprimer un nouveau-né", description = "Supprime l'enregistrement d'un nouveau-né (Admin uniquement)")
    public ResponseEntity<ApiResponse<Void>> deleteNouveauNe(@PathVariable Long id) {
        nouveauNeService.deleteNouveauNe(id);
        return ResponseEntity.ok(ApiResponse.success("Nouveau-né supprimé avec succès"));
    }
}
