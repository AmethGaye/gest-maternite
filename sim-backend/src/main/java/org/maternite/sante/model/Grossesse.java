package org.maternite.sante.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.maternite.sante.model.enums.EtatGrossesse;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "grossesses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grossesse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patiente_id", nullable = false)
    private Patiente patiente;

    @Column(nullable = false)
    private LocalDate dateDebut;

    private LocalDate datePrevue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatGrossesse etatGrossesse;

    @Column(nullable = false)
    private Integer nombreFoetus = 1;

    @OneToOne(mappedBy = "grossesse", cascade = CascadeType.ALL)
    private Accouchement accouchement;

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
