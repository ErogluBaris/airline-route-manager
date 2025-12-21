package com.thy.airlineroutemanager.service;

import com.thy.airlineroutemanager.repository.TransportationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransportationCheckService {

    private final TransportationRepository transportationRepository;

    public boolean validate(Long locationId) {
        return !transportationRepository.existsByOriginLocationOrDestinationLocation(locationId, locationId);
    }

    public void deleteTransportationsByLocationId(Long locationId) {
        transportationRepository.deleteByOriginLocationOrDestinationLocation(locationId, locationId);
    }
}
