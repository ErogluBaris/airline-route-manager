package com.thy.airlineroutemanager.mapper;

import com.thy.airlineroutemanager.dto.LocationDto;
import com.thy.airlineroutemanager.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {

    LocationDto toDto(Location location);

    Location toEntity(LocationDto locationDto);
}
