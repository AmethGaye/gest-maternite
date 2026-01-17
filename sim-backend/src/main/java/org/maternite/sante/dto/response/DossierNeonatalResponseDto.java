package org.maternite.sante.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class DossierNeonatalResponseDto {
    private Long id;
    private Long nouveauNeId;
    private String nouveauNeNom;
    private String nouveauNePrenom;
    private String facteurRh;
    private Boolean reanimation;
    private Boolean detresseRespiratoire;
    private Boolean testGuthrie;
    private Boolean depistage;
    private String observations;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
