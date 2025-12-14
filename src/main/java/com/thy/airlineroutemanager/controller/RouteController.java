package com.thy.airlineroutemanager.controller;

import com.thy.airlineroutemanager.dto.RouteDto;
import com.thy.airlineroutemanager.request.RouteRequest;
import com.thy.airlineroutemanager.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/route")
@RestController
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    public List<RouteDto> findRoutes(@RequestBody RouteRequest request) {
        return routeService.findRoutes(request);
    }
}
