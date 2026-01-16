package org.maternite.sante.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "dossiers_neonatals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DossierNeonatal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "nouveau_ne_id", nullable = false)
    private NouveauNe nouveauNe;

    @Column(nullable = false)
    private LocalDateTime dateCreation;

    private String facteurRh;

    private Boolean reanimation = false;

    private Boolean detresseRespiratoire = false;

    private Boolean testGuthrie = false;

    private Boolean depistage = false;

    @Column(columnDefinition = "TEXT")
    private String observations;

    private LocalDateTime dateModification;

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
