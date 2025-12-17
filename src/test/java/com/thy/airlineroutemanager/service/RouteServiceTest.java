package com.thy.airlineroutemanager.service;

import com.thy.airlineroutemanager.dto.RouteDto;
import com.thy.airlineroutemanager.dto.TransportationDto;
import com.thy.airlineroutemanager.entity.Transportation;
import com.thy.airlineroutemanager.enums.TransportationType;
import com.thy.airlineroutemanager.mapper.TransportationMapper;
import com.thy.airlineroutemanager.request.RouteRequest;
import com.thy.airlineroutemanager.response.RouteResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @InjectMocks
    private RouteService routeService;
    @Mock
    private TransportationService transportationService;
    @Mock
    private TransportationMapper transportationMapper;
    @Mock
    private TransportationLocationEnricher locationEnricher;

    @Test
    void findRoutes_whenEverythingIsValid_ThenVerifyHappyPath() {
        Long originId = 1L;
        Long destinationId = 3L;

        RouteRequest request = new RouteRequest();
        request.setOriginLocationId(originId);
        request.setDestinationLocationId(destinationId);
        request.setDate(LocalDate.of(2025, 7, 21));

        int operatingDay = request.getDate().getDayOfWeek().getValue();

        Transportation t1 = new Transportation();
        t1.setOriginLocation(1L);
        t1.setDestinationLocation(2L);
        t1.setTransportationType(TransportationType.BUS);

        Transportation t2 = new Transportation();
        t2.setOriginLocation(2L);
        t2.setDestinationLocation(3L);
        t2.setTransportationType(TransportationType.FLIGHT);

        Mockito.when(transportationService.findByOriginOrDestination(originId, destinationId, operatingDay))
                .thenReturn(new ArrayList<>(List.of(t1)));

        Mockito.when(transportationService.findByOriginsAndDestinationsAndTransportationType(
                Set.of(2L),
                Collections.emptySet(),
                TransportationType.FLIGHT,
                operatingDay)).thenReturn(new ArrayList<>(List.of(t2)));

        TransportationDto dto1 = new TransportationDto();
        TransportationDto dto2 = new TransportationDto();

        Mockito.when(transportationMapper.toDtoList(List.of(t1, t2)))
                .thenReturn(new ArrayList<>(List.of(dto1, dto2)));

        RouteResponse response = routeService.findRoutes(request);

        assertNotNull(response);
        assertNotNull(response.getRoutes());
        assertEquals(1, response.getRoutes().size());
        RouteDto routeDto = response.getRoutes().getFirst();
        assertEquals(2, routeDto.getTransportations().size());

        Mockito.verify(transportationService).findByOriginOrDestination(originId, destinationId, operatingDay);
        Mockito.verify(transportationService).findByOriginsAndDestinationsAndTransportationType(Set.of(2L), Collections.emptySet(), TransportationType.FLIGHT, operatingDay);
        Mockito.verify(transportationMapper).toDtoList(List.of(t1, t2));
        Mockito.verify(locationEnricher).enrich(ArgumentMatchers.anyList());
    }

    @Test
    void findRoutes_whenNoOriginTransportationExists_thenReturnEmpty() {
        Long originId = 1L;
        Long destinationId = 3L;

        RouteRequest request = new RouteRequest();
        request.setOriginLocationId(originId);
        request.setDestinationLocationId(destinationId);
        request.setDate(LocalDate.of(2025, 7, 21));

        int operatingDay = request.getDate().getDayOfWeek().getValue();
        Mockito.when(transportationService.findByOriginOrDestination(originId, destinationId, operatingDay)).thenReturn(Collections.emptyList());

        RouteResponse response = routeService.findRoutes(request);

        assertNotNull(response);
        assertTrue(response.getRoutes().isEmpty());

        Mockito.verify(transportationService).findByOriginOrDestination(originId, destinationId, operatingDay);
        Mockito.verifyNoInteractions(transportationMapper);
        Mockito.verifyNoInteractions(locationEnricher);
    }

    @Test
    void findRoutes_whenMoreThanOneFlightExists_thenVerifyInvalid() {
        Long originId = 1L;
        Long destinationId = 4L;

        RouteRequest request = new RouteRequest();
        request.setOriginLocationId(originId);
        request.setDestinationLocationId(destinationId);
        request.setDate(LocalDate.of(2025, 7, 21));

        int operatingDay = request.getDate().getDayOfWeek().getValue();

        Transportation t1 = new Transportation();
        t1.setOriginLocation(1L);
        t1.setDestinationLocation(2L);
        t1.setTransportationType(TransportationType.FLIGHT);

        Transportation t2 = new Transportation();
        t2.setOriginLocation(2L);
        t2.setDestinationLocation(3L);
        t2.setTransportationType(TransportationType.BUS);

        Transportation t3 = new Transportation();
        t3.setOriginLocation(3L);
        t3.setDestinationLocation(4L);
        t3.setTransportationType(TransportationType.FLIGHT);

        Mockito.when(transportationService.findByOriginOrDestination(originId, destinationId, operatingDay))
                .thenReturn(new ArrayList<>(List.of(t1)));

        Mockito.when(transportationService.findByOriginsAndDestinationsAndTransportationType(
                Set.of(2L),
                Collections.emptySet(),
                TransportationType.FLIGHT,
                operatingDay)).thenReturn(new ArrayList<>(List.of(t2, t3)));

        RouteResponse response = routeService.findRoutes(request);

        assertNotNull(response);
        assertTrue(response.getRoutes().isEmpty());

        Mockito.verifyNoInteractions(transportationMapper);
        Mockito.verifyNoInteractions(locationEnricher);
    }

}
