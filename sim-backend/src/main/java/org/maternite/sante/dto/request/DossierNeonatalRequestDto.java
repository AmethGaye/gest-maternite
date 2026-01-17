package org.maternite.sante.dto.request;

import lombok.Data;

@Data
public class DossierNeonatalRequestDto {

    private String facteurRh;
    private Boolean reanimation = false;
    private Boolean detresseRespiratoire = false;
    private Boolean testGuthrie = false;
    private Boolean depistage = false;
    private String observations;
}
