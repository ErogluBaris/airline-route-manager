package com.thy.airlineroutemanager.service;

import com.thy.airlineroutemanager.dto.LocationDto;
import com.thy.airlineroutemanager.mapper.LocationMapper;
import com.thy.airlineroutemanager.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository repository;
    private final LocationMapper mapper;

    public LocationDto findById(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new RuntimeException("Record Not Found")));
    }

    public LocationDto save(LocationDto transportationDto) {
        return mapper.toDto(repository.save(mapper.toEntity(transportationDto)));
    }

    public LocationDto update(LocationDto transportationDto) {
        return save(transportationDto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
