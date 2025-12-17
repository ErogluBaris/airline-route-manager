package com.thy.airlineroutemanager.dto;

import com.thy.airlineroutemanager.enums.TransportationType;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;

@Setter
@Getter
public class TransportationDto {

    private Long id;
    private Integer version;
    private LocationDto originLocation;
    private LocationDto destinationLocation;
    private TransportationType transportationType;
    private List<DayOfWeek> operatingDays;
}
