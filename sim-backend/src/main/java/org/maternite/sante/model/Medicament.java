package org.maternite.sante.model;

import lombok.Data;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.maternite.sante.model.enums.TypeMedicament;

import java.time.LocalDateTime;

@Entity
@Table(name = "medicaments")
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescription;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String dosage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeMedicament type;

    @Column(nullable = false)
    private Integer dureeTraitement;

    @Column(nullable = false)
    private Integer quantite;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
