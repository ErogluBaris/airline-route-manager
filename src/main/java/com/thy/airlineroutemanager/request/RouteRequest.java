package com.thy.airlineroutemanager.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class RouteRequest {

    private Long originLocationId;
    private Long destinationLocationId;
    private LocalDate date;
}
