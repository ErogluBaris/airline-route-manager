package com.thy.airlineroutemanager.service;

import com.thy.airlineroutemanager.dto.LocationDto;
import com.thy.airlineroutemanager.mapper.LocationMapper;
import com.thy.airlineroutemanager.repository.LocationRepository;
import com.thy.airlineroutemanager.request.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<LocationDto> findAll(int pageSize) {
        return mapper.toDtoList(repository.findAllBy(Pageable.ofSize(pageSize)));
    }

    public List<LocationDto> findAllByNameLike(SearchRequest searchRequest) {
        return mapper.toDtoList(repository.findAllByNameLike(searchRequest.getNameLike(), Pageable.ofSize(searchRequest.getPageSize())));
    }

    public Map<Long, LocationDto> findAllByIds(Set<Long> ids) {
        return mapper.toDtoList(repository.findAllById(ids)).stream().collect(Collectors.toMap(LocationDto::getId, locationDto -> locationDto));
    }
}
