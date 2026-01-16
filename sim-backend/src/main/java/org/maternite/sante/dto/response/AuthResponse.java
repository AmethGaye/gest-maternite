package org.maternite.sante.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.maternite.sante.model.enums.RoleType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long userId;
    private String email;
    private String nom;
    private String prenom;
    private RoleType role;
    private String tokenType = "Bearer";
    private String accessToken;
    private String refreshToken;
}
