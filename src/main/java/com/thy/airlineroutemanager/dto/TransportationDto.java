package com.thy.airlineroutemanager.dto;

import com.thy.airlineroutemanager.enums.TransportationType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransportationDto {

    private Long id;
    private String originLocation;
    private String destinationLocation;
    private TransportationType transportationType;
    private String operatingDays;
}
