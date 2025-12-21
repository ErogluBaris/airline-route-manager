package com.thy.airlineroutemanager.service;

import com.thy.airlineroutemanager.constant.ValidationConstants;
import com.thy.airlineroutemanager.dto.LocationDto;
import com.thy.airlineroutemanager.exception.RecordNotFoundException;
import com.thy.airlineroutemanager.exception.TransportationOriginDestException;
import com.thy.airlineroutemanager.mapper.LocationMapper;
import com.thy.airlineroutemanager.repository.LocationRepository;
import com.thy.airlineroutemanager.request.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LocationService {

    private final LocationRepository repository;
    private final LocationMapper locationMapper;
    private final TransportationCheckService transportationCheckService;
    private final PageableFactory pageableFactory;

    public LocationDto findById(Long id) {
        return locationMapper.toDto(repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Record not found for locationId: " + id)));
    }

    public LocationDto save(LocationDto transportationDto) {
        return locationMapper.toDto(repository.save(locationMapper.toEntity(transportationDto)));
    }

    public LocationDto update(LocationDto transportationDto) {
        return save(transportationDto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public boolean deleteWithCheck(Long id) {
        if (transportationCheckService.validate(id)) {
            delete(id);
            return true;
        }

        return false;
    }

    @Transactional
    public void deleteWithTransportation(Long locationId) {
        transportationCheckService.deleteTransportationsByLocationId(locationId);
        repository.deleteById(locationId);
    }

    public Page<LocationDto> findAll(SearchRequest request) {
        return repository.findAllBy(pageableFactory.from(request)).map(locationMapper::toDto);
    }

    public Page<LocationDto> findAllByNameLike(SearchRequest searchRequest) {
        return repository.findAllByNameLike(searchRequest.getNameLike(), pageableFactory.from(searchRequest)).map(locationMapper::toDto);
    }
}
