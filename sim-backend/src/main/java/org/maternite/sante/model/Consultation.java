package org.maternite.sante.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultations")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patiente_id")
    private Patiente patiente;

    @ManyToOne
    @JoinColumn(name = "nouveau_ne_id")
    private NouveauNe nouveauNe;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur personnel;

    @Column(nullable = false)
    private LocalDate dateConsultation;

    private Double poids;

    private Double temperature;

    private Integer tensionArterielle;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}