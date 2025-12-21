package com.thy.airlineroutemanager.dto;

import com.thy.airlineroutemanager.enums.TransportationType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;

@Setter
@Getter
public class TransportationDto {

    private Long id;
    private Integer version;
    @NotNull(message = "Origin Location must be selected.")
    private LocationDto originLocation;
    @NotNull(message = "Destination location must be selected.")
    private LocationDto destinationLocation;
    @NotNull(message = "Transportation type must be given.")
    private TransportationType transportationType;
    private List<DayOfWeek> operatingDays;
}
