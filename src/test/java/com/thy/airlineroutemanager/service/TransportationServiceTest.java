package com.thy.airlineroutemanager.service;

import com.thy.airlineroutemanager.dto.TransportationDto;
import com.thy.airlineroutemanager.entity.Transportation;
import com.thy.airlineroutemanager.mapper.TransportationMapper;
import com.thy.airlineroutemanager.repository.TransportationRepository;
import com.thy.airlineroutemanager.request.SearchRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TransportationServiceTest {

    @InjectMocks
    private TransportationService transportationService;
    @Mock
    private TransportationRepository repository;
    @Mock
    private TransportationMapper mapper;
    @Mock
    private TransportationLocationEnricher locationEnricher;

    @Test
    void findById() {
        Long id = 1L;

        Transportation transportation = new Transportation();
        transportation.setId(id);
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(transportation));
        TransportationDto transportationDto = new TransportationDto();
        transportationDto.setId(id);
        Mockito.when(mapper.toDto(transportation)).thenReturn(transportationDto);

        TransportationDto actualDto = transportationService.findById(id);
        assertEquals(transportationDto.getId(), actualDto.getId());
        Mockito.verify(repository, Mockito.times(1)).findById(id);
        Mockito.verify(mapper, Mockito.times(1)).toDto(transportation);
        Mockito.verify(locationEnricher, Mockito.times(1)).enrich(ArgumentMatchers.anyList());
    }

    @Test
    void save() {
        TransportationDto request = new TransportationDto();

        Transportation transportation = new Transportation();
        Mockito.when(mapper.toEntity(request)).thenReturn(transportation);

        Transportation savedTransportation = new Transportation();
        Mockito.when(repository.save(transportation)).thenReturn(savedTransportation);

        TransportationDto returnedDto = new TransportationDto();
        Mockito.when(mapper.toDto(savedTransportation)).thenReturn(returnedDto);

        transportationService.save(request);
        Mockito.verify(mapper, Mockito.times(1)).toEntity(request);
        Mockito.verify(repository, Mockito.times(1)).save(transportation);
        Mockito.verify(mapper, Mockito.times(1)).toDto(savedTransportation);
    }

    @Test
    void delete() {
        Long id = 1L;

        transportationService.delete(id);
        Mockito.verify(repository, Mockito.times(1)).deleteById(id);
    }

    @Test
    void findAll() {
        int pageSize = 10;

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setPageSize(pageSize);

        List<Transportation> transportations = new ArrayList<>();
        Mockito.when(repository.findAllBy(ArgumentMatchers.any(Pageable.class)))
                .thenReturn(transportations);

        List<TransportationDto> transportationDtoList = new ArrayList<>();
        Mockito.when(mapper.toDtoList(transportations)).thenReturn(transportationDtoList);

        transportationService.findAll(searchRequest);

        Mockito.verify(repository, Mockito.times(1)).findAllBy(ArgumentMatchers.any(Pageable.class));
        Mockito.verify(mapper, Mockito.times(1)).toDtoList(transportations);
        Mockito.verify(locationEnricher, Mockito.times(1)).enrich(transportationDtoList);
    }

    @Test
    void findAllByNameLike() {
        String nameLike = "deneme";
        int pageSize = 10;

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setNameLike(nameLike);
        searchRequest.setPageSize(pageSize);

        List<Transportation> transportations = new ArrayList<>();
        Mockito.when(repository.findAllByOriginOrDestinationNameOrCode(ArgumentMatchers.anyString(), ArgumentMatchers.any(Pageable.class)))
                .thenReturn(transportations);

        List<TransportationDto> transportationDtoList = new ArrayList<>();
        Mockito.when(mapper.toDtoList(transportations)).thenReturn(transportationDtoList);

        transportationService.findAllByNameLike(searchRequest);

        Mockito.verify(repository, Mockito.times(1)).findAllByOriginOrDestinationNameOrCode(ArgumentMatchers.anyString(), ArgumentMatchers.any(Pageable.class));
        Mockito.verify(mapper, Mockito.times(1)).toDtoList(transportations);
        Mockito.verify(locationEnricher, Mockito.times(1)).enrich(transportationDtoList);
    }
}