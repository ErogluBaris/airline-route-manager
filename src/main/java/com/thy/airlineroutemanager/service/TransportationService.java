package com.thy.airlineroutemanager.service;

import com.thy.airlineroutemanager.constant.ValidationConstants;
import com.thy.airlineroutemanager.dto.TransportationDto;
import com.thy.airlineroutemanager.entity.Transportation;
import com.thy.airlineroutemanager.enums.TransportationType;
import com.thy.airlineroutemanager.exception.RecordNotFoundException;
import com.thy.airlineroutemanager.exception.TransportationOriginDestException;
import com.thy.airlineroutemanager.mapper.TransportationMapper;
import com.thy.airlineroutemanager.repository.TransportationRepository;
import com.thy.airlineroutemanager.request.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TransportationService {

    private final TransportationRepository repository;
    private final TransportationMapper transportationMapper;
    private final TransportationLocationEnricher locationEnricher;
    private final PageableFactory pageableFactory;

    public TransportationDto findById(Long id) {
        TransportationDto transportationDto = transportationMapper.toDto(repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found for transportation id: " + id)));
        locationEnricher.enrich(List.of(transportationDto));

        return transportationDto;
    }

    public TransportationDto save(TransportationDto transportationDto) {
        if (validateOriginDestination(transportationDto)) {
            throw new TransportationOriginDestException(ValidationConstants.TRANSPORTATION_ORIGIN_DESTINATION_VALIDATION_MSG);
        }
        return transportationMapper.toDto(repository.save(transportationMapper.toEntity(transportationDto)));
    }

    public TransportationDto update(TransportationDto transportationDto) {
        if (validateOriginDestination(transportationDto)) {
            throw new TransportationOriginDestException(ValidationConstants.TRANSPORTATION_ORIGIN_DESTINATION_VALIDATION_MSG);
        }
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

    public Page<TransportationDto> findAll(SearchRequest request){
            Page<TransportationDto> transportations = repository.findAllBy(pageableFactory.from(request)).map(transportationMapper::toDto);
        locationEnricher.enrich(transportations.getContent());
        return transportations;
    }

    public Page<TransportationDto> findAllByNameLike(SearchRequest request) {
        Page<TransportationDto> transportations = repository.findAllByOriginOrDestinationNameOrCode(request.getNameLike(), pageableFactory.from(request)).map(transportationMapper::toDto);
        locationEnricher.enrich(transportations.getContent());

        return transportations;
    }

    private static boolean validateOriginDestination(TransportationDto transportationDto) {
        return Objects.equals(transportationDto.getOriginLocation().getId(), transportationDto.getDestinationLocation().getId());
    }
}
