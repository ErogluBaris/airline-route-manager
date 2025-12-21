package com.thy.airlineroutemanager.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocationDto {

    private Long id;
    private Integer version;
    @NotNull(message = "Location Name cannot be null")
    private String name;
    private String country;
    private String city;
    @NotNull(message = "Location Code cannot be null")
    private String locationCode;
}
