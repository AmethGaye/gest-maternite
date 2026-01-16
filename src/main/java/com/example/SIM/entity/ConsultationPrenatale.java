package com.example.SIM.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "consultation_prenatales")

public class ConsultationPrenatale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private LocalDate dateConsultation;
    private int ageGestationnel;
    private Double poids;

    @Column(length = 1000)
    private String traitement;

    @Column(length = 1000)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "dossier_id")
    private DossierPatient dossier;

     // Getter et Setter


}
