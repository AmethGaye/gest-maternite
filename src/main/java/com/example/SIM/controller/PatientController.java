package com.example.SIM.controller;

import com.example.SIM.entity.Patient;
import com.example.SIM.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Auteur : Coumba
 * Date : 16/01/2026
 */

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Patient> creerPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.creerPatient(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> consulterPatient(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatient(id));
    }
}
