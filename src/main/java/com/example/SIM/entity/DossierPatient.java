package com.example.SIM.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "dossier_patient")
public class DossierPatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate dateCreation;
    private String statut;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "dossier", cascade = CascadeType.ALL)
    private List<ConsultationPrenatale> consultations;

}
