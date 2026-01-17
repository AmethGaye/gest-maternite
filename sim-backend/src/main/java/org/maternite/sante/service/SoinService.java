package org.maternite.sante.service;

import lombok.RequiredArgsConstructor;
import org.maternite.sante.dto.request.SoinRequestDto;
import org.maternite.sante.dto.response.SoinResponseDto;
import org.maternite.sante.exceptions.ResourceNotFoundException;
import org.maternite.sante.mapper.SoinMapper;
import org.maternite.sante.model.NouveauNe;
import org.maternite.sante.model.Soin;
import org.maternite.sante.repository.NouveauNeRepository;
import org.maternite.sante.repository.SoinRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoinService {

    private final SoinRepository soinRepository;
    private final NouveauNeRepository nouveauNeRepository;
    private final SoinMapper soinMapper;

    @Transactional
    public SoinResponseDto createSoin(SoinRequestDto dto) {
        NouveauNe nouveauNe = nouveauNeRepository.findById(dto.getNouveauNeId())
                .orElseThrow(() -> new ResourceNotFoundException("Nouveau-né non trouvé avec l'ID: " + dto.getNouveauNeId()));

        Soin soin = soinMapper.toEntity(dto);
        soin.setNouveauNe(nouveauNe);

        Soin savedSoin = soinRepository.save(soin);
        return soinMapper.toDto(savedSoin);
    }

    public List<SoinResponseDto> getAllSoins() {
        return soinRepository.findAll().stream()
                .map(soinMapper::toDto)
                .collect(Collectors.toList());
    }

    public SoinResponseDto getSoinById(Long id) {
        Soin soin = soinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Soin non trouvé avec l'ID: " + id));
        return soinMapper.toDto(soin);
    }

    public List<SoinResponseDto> getSoinsByNouveauNe(Long nouveauNeId) {
        return soinRepository.findByNouveauNeId(nouveauNeId).stream()
                .map(soinMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SoinResponseDto updateSoin(Long id, SoinRequestDto dto) {
        Soin soin = soinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Soin non trouvé avec l'ID: " + id));

        NouveauNe nouveauNe = nouveauNeRepository.findById(dto.getNouveauNeId())
                .orElseThrow(() -> new ResourceNotFoundException("Nouveau-né non trouvé avec l'ID: " + dto.getNouveauNeId()));

        soin.setNouveauNe(nouveauNe);
        soin.setTypeSoin(dto.getTypeSoin());
        soin.setPoids(dto.getPoids());
        soin.setTemperature(dto.getTemperature());
        soin.setTensionArterielle(dto.getTensionArterielle());
        soin.setObservations(dto.getObservations());

        Soin updatedSoin = soinRepository.save(soin);
        return soinMapper.toDto(updatedSoin);
    }

    @Transactional
    public void deleteSoin(Long id) {
        if (!soinRepository.existsById(id)) {
            throw new ResourceNotFoundException("Soin non trouvé avec l'ID: " + id);
        }
        soinRepository.deleteById(id);
    }
}
