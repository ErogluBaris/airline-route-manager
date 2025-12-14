package com.thy.airlineroutemanager.service;

import com.thy.airlineroutemanager.dto.RouteDto;
import com.thy.airlineroutemanager.entity.Transportation;
import com.thy.airlineroutemanager.enums.TransportationType;
import com.thy.airlineroutemanager.request.RouteRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RouteService {

    private final TransportationService transportationService;

    public List<RouteDto> findRoutes(RouteRequest request) {
        Long originLocationId = request.getOriginId();
        Long destinationLocationId = request.getDestinationId();
        int requestedOperatingDay = request.getDateTime().getDayOfWeek().getValue();

        List<Transportation> transportationList = transportationService.findByOriginOrDestination(originLocationId, destinationLocationId, requestedOperatingDay);//TODO operationDay kontrol ekle

        List<RouteDto> routeDtoList = new ArrayList<>();
        Set<Long> destinationIdsForOrigin = new HashSet<>();
        Set<Long> originIdsForDestination = new HashSet<>();
        for (Transportation transportation : transportationList) {
            if (isOrigin(originLocationId, transportation)) {
                destinationIdsForOrigin.add(transportation.getDestinationLocation());
            } else if (isDestination(destinationLocationId, transportation)) {
                originIdsForDestination.add(transportation.getOriginLocation());
            }
        }

        transportationList.addAll(transportationService.findByOriginsAndDestinationsAndTransportationType(destinationIdsForOrigin, originIdsForDestination, TransportationType.FLIGHT, requestedOperatingDay));//TODO operationDay kontrol ekle

        return processRoutes(transportationList, originLocationId, destinationLocationId, routeDtoList);
    }

    private List<RouteDto> processRoutes(List<Transportation> transportationList, Long originId, Long destinationId, List<RouteDto> routeDtoList) {
        Map<Long, List<Transportation>> transportationByOrigin = transportationList.stream()
                .collect(Collectors.groupingBy(Transportation::getOriginLocation));

        if (CollectionUtils.isEmpty(transportationByOrigin.get(originId))) {
            return new ArrayList<>();
        }

        processRecursively(transportationByOrigin, transportationByOrigin.get(originId), new ArrayList<>(), destinationId, routeDtoList);

        return routeDtoList;
    }

    private void processRecursively(Map<Long, List<Transportation>> transportationByOrigin, List<Transportation> searchedTransportations, List<Transportation> route, Long destinationId, List<RouteDto> routeDtoList) {
        if ((route.size() == 3 && !CollectionUtils.isEmpty(route) && !isDestination(destinationId, route.getLast()))) {
            return;
        }

        if (!CollectionUtils.isEmpty(route) && isRouteValid(route, destinationId)) {
            routeDtoList.add(new RouteDto(new ArrayList<>(route)));
            return;
        }

        for (Transportation transportation : searchedTransportations) {
            List<Transportation> nextTransportations = transportationByOrigin.get(transportation.getDestinationLocation());
            if (nextTransportations == null) {
                nextTransportations = new ArrayList<>();
            }
            if (route.isEmpty()) {
                route.add(transportation);
                processRecursively(transportationByOrigin, nextTransportations, route, destinationId, routeDtoList);
            } else if (route.size() == 1) {
                route.add(transportation);
                processRecursively(transportationByOrigin, nextTransportations.stream()
                        .filter(iterated -> iterated.getTransportationType() != TransportationType.FLIGHT)
                        .toList(), route, destinationId, routeDtoList);
            } else if (route.size() == 2) {
                route.add(transportation);
                processRecursively(transportationByOrigin, new ArrayList<>(), route, destinationId, routeDtoList);
            }

            if (!route.isEmpty()) {
                route.removeLast();
            }
        }
    }

    private static boolean isRouteValid(List<Transportation> route, Long destinationId) {
        return route.stream().filter(transportation -> transportation.getTransportationType() == TransportationType.FLIGHT).count() == 1
                && isDestination(destinationId, route.getLast());
    }

    private static boolean isOrigin(Long originLocationId, Transportation transportation) {
        return Objects.equals(transportation.getOriginLocation(), originLocationId);
    }

    private static boolean isDestination(Long destinationLocationId, Transportation transportation) {
        return Objects.equals(transportation.getDestinationLocation(), destinationLocationId);
    }
}
