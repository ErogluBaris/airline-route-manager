package com.thy.airlineroutemanager.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class RouteRequest {

    private Long originId;
    private Long destinationId;
    private LocalDateTime dateTime;
}
