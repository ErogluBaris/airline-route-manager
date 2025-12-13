package com.thy.airlineroutemanager.service;

import com.thy.airlineroutemanager.dto.TransportationDto;
import com.thy.airlineroutemanager.mapper.TransportationMapper;
import com.thy.airlineroutemanager.repository.TransportationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransportationService {

    private final TransportationRepository repository;
    private final TransportationMapper mapper;

    public TransportationDto findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new RuntimeException("Record Not Found")));
    }

    public TransportationDto save(TransportationDto transportationDto) {
        return mapper.toDto(repository.save(mapper.toEntity(transportationDto)));
    }

    public TransportationDto update(TransportationDto transportationDto) {
        return save(transportationDto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
