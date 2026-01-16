package org.maternite.sante.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.maternite.sante.model.enums.StatutSalle;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "salles_accouchement")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SalleAccouchement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer numeroSalle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutSalle statut;

    private Boolean estDisponible = true;

    @OneToMany(mappedBy = "salle")
    private List<Accouchement> accouchements;

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