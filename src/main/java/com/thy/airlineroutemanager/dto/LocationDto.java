package com.thy.airlineroutemanager.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LocationDto {

    private Long id;
    private Integer version;
    private String name;
    private String country;
    private String city;
    private String locationCode;
}
