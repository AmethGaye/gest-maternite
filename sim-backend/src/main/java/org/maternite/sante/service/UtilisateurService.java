package org.maternite.sante.service;

import org.maternite.sante.dto.request.RegisterRequest;
import org.maternite.sante.dto.request.UtilisateurUpdateRequest;
import org.maternite.sante.dto.response.UtilisateurResponseDto;

import java.util.List;

public interface UtilisateurService {
    UtilisateurResponseDto createUser(RegisterRequest request);
    List<UtilisateurResponseDto> getAllUsers();
    UtilisateurResponseDto getUserById(Long id);
    UtilisateurResponseDto getUserByEmail(String email);
    UtilisateurResponseDto updateUser(Long id, UtilisateurUpdateRequest request);
    void deleteUser(Long id);
    UtilisateurResponseDto toggleDisponibilite(Long id);
}
