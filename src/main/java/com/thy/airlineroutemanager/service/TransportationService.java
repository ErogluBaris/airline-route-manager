package com.thy.airlineroutemanager.service;

import com.thy.airlineroutemanager.dto.TransportationDto;
import com.thy.airlineroutemanager.entity.Transportation;
import com.thy.airlineroutemanager.enums.TransportationType;
import com.thy.airlineroutemanager.mapper.TransportationMapper;
import com.thy.airlineroutemanager.repository.TransportationRepository;
import com.thy.airlineroutemanager.request.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TransportationService {

    private final TransportationRepository repository;
    private final TransportationMapper mapper;
    private final TransportationLocationEnricher locationEnricher;

    public TransportationDto findById(Long id) {
        TransportationDto transportationDto = mapper.toDto(repository.findById(id).orElseThrow(() -> new RuntimeException("Record Not Found")));
        locationEnricher.enrich(List.of(transportationDto));

        return transportationDto;
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

    public List<Transportation> findByOriginOrDestination(Long originId, Long destinationId, Integer operatingDay) {
        return repository.findAllByOriginLocationOrDestinationLocation(originId, destinationId, operatingDay);
    }

    public List<Transportation> findByOriginsAndDestinationsAndTransportationType(Set<Long> originIds, Set<Long> destinationIds, TransportationType type, Integer operatingDay) {
        return repository.findAllByOriginLocationInAndDestinationLocationInAndTransportationTypeAndOperatingDay(originIds, destinationIds, type.name(), operatingDay);
    }

    public List<TransportationDto> findAll(SearchRequest request){
            List<TransportationDto> transportations = mapper.toDtoList(repository.findAllBy(Pageable.ofSize(request.getPageSize())));
        locationEnricher.enrich(transportations);
        return transportations;
    }

    public List<TransportationDto> findAllByNameLike(SearchRequest request) {
        List<TransportationDto> transportations = mapper.toDtoList(repository.findAllByOriginOrDestinationNameOrCode(request.getNameLike(), Pageable.ofSize(request.getPageSize())));
        locationEnricher.enrich(transportations);

        return transportations;
    }


}
