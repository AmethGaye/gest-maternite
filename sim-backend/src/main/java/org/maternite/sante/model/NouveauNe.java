package org.maternite.sante.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.maternite.sante.model.enums.Sexe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "nouveau_nes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NouveauNe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accouchement_id", nullable = false)
    private Accouchement accouchement;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexe sexe;

    @Column(nullable = false)
    private Double poidsNaissance;

    @Column(nullable = false)
    private Double tailleNaissance;

    @Column(nullable = false)
    private LocalDate dateNaissance;

    @OneToOne(mappedBy = "nouveauNe", cascade = CascadeType.ALL)
    private DossierNeonatal dossierNeonatal;

    @OneToMany(mappedBy = "nouveauNe", cascade = CascadeType.ALL)
    private List<Consultation> consultations;

    @OneToMany(mappedBy = "nouveauNe", cascade = CascadeType.ALL)
    private List<Soin> soins;

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
