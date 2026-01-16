package org.maternite.sante.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "dossiers_medicaux")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DossierMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "patiente_id", nullable = false)
    private Patiente patiente;

    private String antecedentsMedicaux;

    private String antecedentsChirurgicaux;

    private String antecedentsObstetricaux;

    private String allergies;

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
