package com.thy.airlineroutemanager.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class RouteRequest {

    @NotNull(message = "Origin location cannot be null.")
    private Long originLocationId;
    @NotNull(message = "Destination location cannot be null.")
    private Long destinationLocationId;
    private LocalDate date;
}
