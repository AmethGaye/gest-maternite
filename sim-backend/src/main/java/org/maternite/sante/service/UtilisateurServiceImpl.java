package org.maternite.sante.service;

import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.RegisterRequest;
import org.maternite.sante.dto.request.UtilisateurUpdateRequest;
import org.maternite.sante.dto.response.UtilisateurResponseDto;
import org.maternite.sante.exceptions.BadRequestException;
import org.maternite.sante.exceptions.ResourceNotFoundException;
import org.maternite.sante.mapper.UtilisateurMapper;
import org.maternite.sante.model.Utilisateur;
import org.maternite.sante.repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UtilisateurResponseDto createUser(RegisterRequest request) {
        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Cet email est déjà utilisé");
        }

        if (request.getMatricule() != null && utilisateurRepository.existsByMatricule(request.getMatricule())) {
            throw new BadRequestException("Ce matricule est déjà utilisé");
        }

        Utilisateur utilisateur = Utilisateur.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .telephone(request.getTelephone())
                .matricule(request.getMatricule())
                .estDisponible(true)
                .build();

        return utilisateurMapper.toDto(utilisateurRepository.save(utilisateur));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilisateurResponseDto> getAllUsers() {
        return utilisateurRepository.findAll().stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UtilisateurResponseDto getUserById(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", id));
        return utilisateurMapper.toDto(utilisateur);
    }

    @Override
    @Transactional(readOnly = true)
    public UtilisateurResponseDto getUserByEmail(String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "email", email));
        return utilisateurMapper.toDto(utilisateur);
    }

    @Override
    public UtilisateurResponseDto updateUser(Long id, UtilisateurUpdateRequest request) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", id));

        // Vérifier si l'email a changé et s'il est déjà utilisé
        if (!utilisateur.getEmail().equals(request.getEmail()) && 
            utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Cet email est déjà utilisé");
        }

        // Vérifier si le matricule a changé et s'il est déjà utilisé
        if (request.getMatricule() != null && 
            (utilisateur.getMatricule() == null || !utilisateur.getMatricule().equals(request.getMatricule())) &&
            utilisateurRepository.existsByMatricule(request.getMatricule())) {
            throw new BadRequestException("Ce matricule est déjà utilisé");
        }

        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setRole(request.getRole());
        utilisateur.setTelephone(request.getTelephone());
        utilisateur.setMatricule(request.getMatricule());
        if (request.getEstDisponible() != null) {
            utilisateur.setEstDisponible(request.getEstDisponible());
        }

        return utilisateurMapper.toDto(utilisateurRepository.save(utilisateur));
    }

    @Override
    public void deleteUser(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Utilisateur", "id", id);
        }
        utilisateurRepository.deleteById(id);
    }

    @Override
    public UtilisateurResponseDto toggleDisponibilite(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur", "id", id));
        
        utilisateur.setEstDisponible(!utilisateur.getEstDisponible());
        return utilisateurMapper.toDto(utilisateurRepository.save(utilisateur));
    }
}
