package com.example.SIM.entity;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table (name ="Patient" )
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String nom;
    private LocalDate dateNaissance;
    private String telephone;
    private String adresse;
    private String groupeSanguin;

    @Column(length = 1000)
    private String antecedents;

    @OneToOne(mappedBy = "patient" , cascade = CascadeType.ALL)
    private DossierPatient dossier;


}
