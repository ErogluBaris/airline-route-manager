package com.thy.airlineroutemanager.service;

import com.thy.airlineroutemanager.dto.LocationDto;
import com.thy.airlineroutemanager.dto.TransportationDto;
import com.thy.airlineroutemanager.entity.Location;
import com.thy.airlineroutemanager.mapper.LocationMapper;
import com.thy.airlineroutemanager.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransportationLocationEnricherTest {

    @InjectMocks
    private TransportationLocationEnricher transportationLocationEnricher;
    @Mock
    private LocationRepository locationRepository;
    @Mock
    private LocationMapper locationMapper;

    @Test
    void enrich() {
        LocationDto origin = new LocationDto();
        origin.setId(1L);
        LocationDto destination = new LocationDto();
        destination.setId(2L);

        TransportationDto transportationDto = new TransportationDto();
        transportationDto.setId(1L);
        transportationDto.setOriginLocation(origin);
        transportationDto.setDestinationLocation(destination);

        List<LocationDto> locationDtoList = new ArrayList<>();
        LocationDto originLocationDto = new LocationDto();
        originLocationDto.setId(1L);
        originLocationDto.setName("origin");
        LocationDto destinationLocationDto = new LocationDto();
        originLocationDto.setId(2L);
        originLocationDto.setName("destination");

        locationDtoList.add(originLocationDto);
        locationDtoList.add(destinationLocationDto);

        List<Location> locations = new ArrayList<>();
        Mockito.when(locationRepository.findAllById(ArgumentMatchers.anySet())).thenReturn(locations);
        Mockito.when(locationMapper.toDtoList(locations)).thenReturn(locationDtoList);

        transportationLocationEnricher.enrich(List.of(transportationDto));
        assertEquals(transportationDto.getOriginLocation().getName(), originLocationDto.getName());
        assertEquals(transportationDto.getDestinationLocation().getName(), destinationLocationDto.getName());
        assertEquals(transportationDto.getOriginLocation().getId(), originLocationDto.getId());
        assertEquals(transportationDto.getDestinationLocation().getId(), destinationLocationDto.getId());
    }

    @Test
    void enrich_whenIdsEmpty_thenAssertReturnedWithoutEnrichment() {
        TransportationDto transportationDto = new TransportationDto();

        transportationLocationEnricher.enrich(List.of(transportationDto));

        assertNull(transportationDto.getOriginLocation());
        assertNull(transportationDto.getDestinationLocation());
    }
}