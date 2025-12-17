package com.thy.airlineroutemanager.service;

import com.thy.airlineroutemanager.dto.LocationDto;
import com.thy.airlineroutemanager.dto.TransportationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class TransportationLocationEnricher {

    private final LocationService locationService;

    public void enrich(List<TransportationDto> transportationDtoList) {
        Set<Long> locationIds = transportationDtoList.stream()
                .filter(transportationDto -> transportationDto.getDestinationLocation() != null && transportationDto.getOriginLocation() != null)
                .flatMap(transportationDto -> Stream.of(transportationDto.getOriginLocation().getId(), transportationDto.getDestinationLocation().getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        if (locationIds.isEmpty()) {
            return;
        }

        Map<Long, LocationDto> locationMap = locationService.findAllByIds(locationIds);

        for (TransportationDto transportationDto : transportationDtoList) {
            transportationDto.setOriginLocation(locationMap.get(transportationDto.getOriginLocation().getId()));
            transportationDto.setDestinationLocation(locationMap.get(transportationDto.getDestinationLocation().getId()));
        }
    }
}
