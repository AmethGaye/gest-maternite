package org.maternite.sante.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.maternite.sante.model.enums.TypeAccouchement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "accouchements")
@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Accouchement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "grossesse_id", nullable = false)
    private Grossesse grossesse;

    @Column(nullable = false)
    private LocalTime heureDebut;

    private LocalTime heureFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeAccouchement type;

    @Column(columnDefinition = "TEXT")
    private String complications;

    @OneToMany(mappedBy = "accouchement", cascade = CascadeType.ALL)
    private List<NouveauNe> nouveauNes;

    @ManyToOne
    @JoinColumn(name = "salle_id")
    private SalleAccouchement salle;

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