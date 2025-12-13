package com.thy.airlineroutemanager.controller;

import com.thy.airlineroutemanager.dto.LocationDto;
import com.thy.airlineroutemanager.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public LocationDto create(@RequestBody LocationDto locationDto){
        return locationService.save(locationDto);
    }

    @PutMapping
    public LocationDto update(@RequestBody LocationDto locationDto){
        return locationService.update(locationDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        locationService.delete(id);
    }
}
