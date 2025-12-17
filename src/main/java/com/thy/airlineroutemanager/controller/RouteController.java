package com.thy.airlineroutemanager.controller;

import com.thy.airlineroutemanager.request.RouteRequest;
import com.thy.airlineroutemanager.response.RouteResponse;
import com.thy.airlineroutemanager.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasAnyRole('ADMIN', 'AGENCY')")
@RequiredArgsConstructor
@RequestMapping("/route")
@RestController
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    public RouteResponse findRoutes(@RequestBody RouteRequest request) {
        return routeService.findRoutes(request);
    }
}
