package com.thy.airlineroutemanager.mapper;

import com.thy.airlineroutemanager.dto.LocationDto;
import com.thy.airlineroutemanager.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationMapper {

    LocationDto toDto(Location location);

    Location toEntity(LocationDto locationDto);

    List<LocationDto> toDtoList(List<Location> location);

    List<Location> toEntityList(List<LocationDto> locationDtoList);
}
