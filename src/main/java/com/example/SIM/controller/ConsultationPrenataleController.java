package com.example.SIM.controller;

import com.example.SIM.entity.ConsultationPrenatale;
import com.example.SIM.service.ConsultationPrenataleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Auteur : Coumba
 * Date : 16/01/2026
 */

@RestController
@RequestMapping("/api/consultations")
public class ConsultationPrenataleController {


    private final ConsultationPrenataleService consultationService;

    public ConsultationPrenataleController(
            ConsultationPrenataleService consultationService) {
        this.consultationService = consultationService;
    }

    @PostMapping("/patient/{patientId}")
    public ResponseEntity<ConsultationPrenatale> ajouterConsultation(
            @PathVariable Long patientId,
            @RequestBody ConsultationPrenatale consultation) {

        return ResponseEntity.ok(
                consultationService.ajouterConsultation(patientId, consultation)
        );
    }

    @GetMapping("/dossier/{dossierId}")
    public ResponseEntity<List<ConsultationPrenatale>> getConsultations(
            @PathVariable Long dossierId) {

        return ResponseEntity.ok(
                consultationService.getConsultations(dossierId)
        );
    }

    }
