package com.thy.airlineroutemanager.controller;

import com.thy.airlineroutemanager.dto.TransportationDto;
import com.thy.airlineroutemanager.request.SearchRequest;
import com.thy.airlineroutemanager.service.TransportationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        //TODO origin ve destination aynı olamaz
        return transportationService.save(locationDto);
    }

    @PutMapping
    public TransportationDto update(@RequestBody TransportationDto locationDto){
        //TODO origin ve destination aynı olamaz
        return transportationService.update(locationDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        transportationService.delete(id);
    }

    @PostMapping("/find-all")
    public List<TransportationDto> findAll(@RequestBody SearchRequest request){
        return transportationService.findAll(request);
    }

    @PostMapping("/search")
    public List<TransportationDto> search(@RequestBody SearchRequest request){
        //TODO bir sonraki sayfaya gelebilecek şekilde yap. Şu an pagination yok fe tarafına da ekle.
        return transportationService.findAllByNameLike(request);
    }
}
