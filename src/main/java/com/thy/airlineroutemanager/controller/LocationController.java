package com.thy.airlineroutemanager.controller;

import com.thy.airlineroutemanager.dto.LocationDto;
import com.thy.airlineroutemanager.request.SearchRequest;
import com.thy.airlineroutemanager.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/location")
@RestController
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/{id}")
    public LocationDto getLocationById(@PathVariable("id") Long id){
        return locationService.findById(id);
    }

    @PostMapping
    public LocationDto create(@Valid @RequestBody LocationDto locationDto){
        return locationService.save(locationDto);
    }

    @PutMapping
    public LocationDto update(@Valid @RequestBody LocationDto locationDto){
        return locationService.update(locationDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        locationService.delete(id);
    }

    @DeleteMapping("/delete-with-check/{id}")
    public boolean deleteWithCheck(@PathVariable("id") Long id){
        return locationService.deleteWithCheck(id);
    }

    @DeleteMapping("/delete-with-transportation/{id}")
    public void deleteWithTransportation(@PathVariable("id") Long id){
        locationService.deleteWithTransportation(id);
    }

    @PostMapping("/find-all")
    public Page<LocationDto> findAll(@RequestBody SearchRequest request){
        return locationService.findAll(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'AGENCY')")
    @PostMapping("/search")
    public Page<LocationDto> search(@RequestBody SearchRequest request){
        return locationService.findAllByNameLike(request);
    }
}
