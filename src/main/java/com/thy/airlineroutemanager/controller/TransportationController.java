package com.thy.airlineroutemanager.controller;

import com.thy.airlineroutemanager.dto.TransportationDto;
import com.thy.airlineroutemanager.request.SearchRequest;
import com.thy.airlineroutemanager.service.TransportationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasAnyRole('ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/transportation")
@RestController
public class TransportationController {

    private final TransportationService transportationService;

    @GetMapping("/{id}")
    public TransportationDto getLocationById(@PathVariable("id") Long id){
        return transportationService.findById(id);
    }

    @PostMapping
    public TransportationDto create(@RequestBody TransportationDto locationDto){
        return transportationService.save(locationDto);
    }

    @PutMapping
    public TransportationDto update(@RequestBody TransportationDto locationDto){
        return transportationService.update(locationDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        transportationService.delete(id);
    }

    @PostMapping("/find-all")
    public Page<TransportationDto> findAll(@RequestBody SearchRequest request){
        return transportationService.findAll(request);
    }

    @PostMapping("/search")
    public Page<TransportationDto> search(@RequestBody SearchRequest request){
        return transportationService.findAllByNameLike(request);
    }
}
